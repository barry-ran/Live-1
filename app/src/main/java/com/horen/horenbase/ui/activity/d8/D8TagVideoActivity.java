package com.horen.horenbase.ui.activity.d8;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.rx.BaseObserver;
import com.horen.base.ui.BaseActivity;
import com.horen.horenbase.R;
import com.horen.horenbase.api.Api;
import com.horen.horenbase.api.UrlConstant;
import com.horen.horenbase.bean.d8.SearchBean;
import com.horen.horenbase.bean.d8.VideoBean;
import com.horen.horenbase.rx.RxHelper;
import com.horen.horenbase.ui.adapter.SearchAdapter;
import com.horen.horenbase.utils.SnackbarUtils;
import com.horen.horenbase.utils.UniCodeUtils;
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
public class D8TagVideoActivity extends BaseActivity implements OnRefreshLoadmoreListener {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    AppCompatImageView ivRight;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    private SearchAdapter searchAdapter;

    public int page = 1;
    public int perPage = 10;
    private String tag_name;

    public static void startAction(Context context, String tag_name) {
        Intent intent = new Intent();
        intent.setClass(context, D8TagVideoActivity.class);
        intent.putExtra("tag_name", tag_name);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_d8_tag_videol;
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
        searchAdapter = new SearchAdapter(R.layout.item_search, new ArrayList<VideoBean>());
        searchAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(searchAdapter);
        refresh.setOnRefreshLoadmoreListener(this);
        searchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoBean bean = searchAdapter.getData().get(position);
                D8VideoDetailActivity.startAction(mContext, bean.getTitle(),
                        bean.getId_encrypt(), UniCodeUtils.replaceHttpUrl(bean.getThumb_href()));
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
                            SnackbarUtils.show(D8TagVideoActivity.this, getString(R.string.no_data));
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
                        SnackbarUtils.show(D8TagVideoActivity.this, message);
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
