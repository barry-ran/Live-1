package com.horen.horenbase.ui.activity.live;

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
import com.horen.base.rx.RxSchedulers;
import com.horen.base.ui.BaseActivity;
import com.horen.horenbase.R;
import com.horen.horenbase.api.Api;
import com.horen.horenbase.api.UrlConstant;
import com.horen.horenbase.bean.live.LiveDetail;
import com.horen.horenbase.bean.live.LivePlatform;
import com.horen.horenbase.ui.adapter.DetailAdapter;
import com.horen.horenbase.utils.SnackbarUtils;
import com.horen.horenbase.utils.UniCodeUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.litepal.LitePal;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class LiveDetailActivity extends BaseActivity implements OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    AppCompatImageView ivRight;
    private DetailAdapter adapter;
    private String title;
    private String url;
    private String imageUrl;
    private LivePlatform platform;

    public static void startAction(Context context, String url, String title, String imageUrl) {
        Intent intent = new Intent();
        intent.setClass(context, LiveDetailActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.putExtra("imageUrl", imageUrl);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_live_detail;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        imageUrl = getIntent().getStringExtra("imageUrl");
        ivRight.setVisibility(View.VISIBLE);
        initToolbar(toolBar, false);
        toolBar.setSubtitle(title);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        adapter = new DetailAdapter(R.layout.item, new ArrayList<LiveDetail.ZhuboBean>());
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(adapter);
        refresh.setOnRefreshListener(this);
        getData();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LiveDetail.ZhuboBean zhuboBean = LiveDetailActivity.this.adapter.getData().get(position);
                VideoActivity.startAction(mContext, zhuboBean.getAddress(), UniCodeUtils.unicodeToString(zhuboBean.getTitle()),zhuboBean.getImg());
            }
        });
        platform = LitePal.where("url=?", url)
                .findFirst(LivePlatform.class);
        checkCollectState(platform);
    }

    private void getData() {
        mRxManager.add(Api.getService(UrlConstant.LIVE).getDetailList(url)
                .compose(RxSchedulers.<LiveDetail>io_main())
                .subscribeWith(new BaseObserver<LiveDetail>(mContext, true) {
                    @Override
                    protected void _onNext(LiveDetail bean) {
                        adapter.setNewData(bean.getZhubo());
                        refresh.finishRefresh();
                    }

                    @Override
                    protected void _onError(String message) {
                        refresh.finishRefresh();
                    }
                }));
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getData();
    }

    @OnClick(R.id.iv_right)
    public void onViewClicked() {
        if (platform == null) { // 没有收藏过
            platform = new LivePlatform.Builder()
                    .setUrl(url)
                    .setImageUrl(imageUrl)
                    .setName(title)
                    .builder();
            if (platform.save()) SnackbarUtils.show(this, getString(R.string.collect_success));
        } else { // 已经收藏，删除收藏
            platform.delete();
            platform = null;
            SnackbarUtils.show(this, getString(R.string.collect_cancle));
        }
        checkCollectState(platform);
    }

    private void checkCollectState(LivePlatform platform) {
        if (platform == null) {
            ivRight.setImageResource(R.drawable.icon_un_collect);
        } else {
            ivRight.setImageResource(R.drawable.icon_collect);
        }
    }
}
