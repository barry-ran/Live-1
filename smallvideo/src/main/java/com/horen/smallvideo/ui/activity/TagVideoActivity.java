package com.horen.smallvideo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.billy.cc.core.component.CC;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.app.CCName;
import com.horen.base.net.Api;
import com.horen.base.net.UrlConstant;
import com.horen.base.rx.BaseObserver;
import com.horen.base.rx.RxHelper;
import com.horen.base.ui.BaseActivity;
import com.horen.base.util.SnackbarUtils;
import com.horen.base.util.UniCodeUtils;
import com.horen.domain.d8.SearchBean;
import com.horen.domain.d8.VideoBean;
import com.horen.smallvideo.R;
import com.horen.smallvideo.R2;
import com.horen.smallvideo.adapter.SearchVideoAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author :ChenYangYi
 * @date :2018/07/03/10:06
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class TagVideoActivity extends BaseActivity implements OnRefreshLoadmoreListener {


    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.iv_right)
    AppCompatImageView ivRight;
    @BindView(R2.id.tool_bar)
    Toolbar toolBar;
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R2.id.refresh)
    SmartRefreshLayout refresh;
    private SearchVideoAdapter searchAdapter;

    public int page = 1;
    public int perPage = 10;
    private String tag_name;


    @Override
    public int getLayoutId() {
        return R.layout.smallvideo_activity_tag_video;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        tag_name = getIntent().getStringExtra("tag_name");
        initToolbar(toolBar, false);
        tvTitle.setText(tag_name);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        searchAdapter = new SearchVideoAdapter(R.layout.smallvideo_item_search, new ArrayList<VideoBean>());
        searchAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(searchAdapter);
        refresh.setOnRefreshLoadmoreListener(this);
        searchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoBean bean = searchAdapter.getData().get(position);
                CC.obtainBuilder(CCName.SMALL_VIDEO)
                        .setActionName(CCName.SMALL_VIDEO_DETAIL)
                        .addParam("title", bean.getTitle())
                        .addParam("url", bean.getId_encrypt())
                        .addParam("imageUrl", UniCodeUtils.replaceHttpUrl(bean.getThumb_href()))
                        .build()
                        .callAsync();
            }
        });
        getData();
    }

    /**
     * 视频
     */
    private void getData() {
        if (page < 1) page = 1;
        mRxManager.add(Api.getService(UrlConstant.D8_VIDEO).d8TagVideo(tag_name, page, perPage)
                .compose(RxHelper.<SearchBean>handleResult())
                .subscribeWith(new BaseObserver<SearchBean>(mContext, true) {
                    @Override
                    protected void _onNext(SearchBean search) {
                        if (search.getVideos().size() <= 0) {
                            page--;
                            SnackbarUtils.show(TagVideoActivity.this, getString(R.string.no_data));
                            refresh.finishLoadmore();
                            return;
                        }
                        // 加载更多
                        if (page > 1) {
                            searchAdapter.addData(search.getVideos());
                            refresh.finishLoadmore();
                        } else {
                            searchAdapter.setNewData(search.getVideos());
                            refresh.finishRefresh();
                        }
                        tvTitle.setText(tag_name + "(" + (search.getPaginator().getTotal()) + ")");
                    }

                    @Override
                    protected void _onError(String message) {
                        page--;
                        SnackbarUtils.show(TagVideoActivity.this, message);
                        refresh.finishRefresh();
                        refresh.finishLoadmore();
                    }
                }));
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        getData();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        getData();
    }

}
