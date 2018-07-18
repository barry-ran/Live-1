package com.horen.live.ui.activity;

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
import com.horen.base.net.Api;
import com.horen.base.net.UrlConstant;
import com.horen.domain.live.LiveDetail;
import com.horen.domain.live.LivePlatform;
import com.horen.base.util.SnackbarUtils;
import com.horen.base.util.UniCodeUtils;
import com.horen.live.R;
import com.horen.live.adapter.DetailAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.litepal.LitePal;

import java.util.ArrayList;


public class LiveDetailActivity extends BaseActivity implements OnRefreshListener, View.OnClickListener {

    private SmartRefreshLayout refresh;
    private RecyclerView recyclerView;
    private Toolbar toolBar;
    private TextView tvTitle;
    private AppCompatImageView ivRight;
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
        return R.layout.live_activity_live_detail;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        refresh = (SmartRefreshLayout) findViewById(R.id.refresh);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        toolBar = (Toolbar) findViewById(R.id.tool_bar);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivRight = (AppCompatImageView) findViewById(R.id.iv_right);
        ivRight.setOnClickListener(this);
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        imageUrl = getIntent().getStringExtra("imageUrl");
        ivRight.setVisibility(View.VISIBLE);
        initToolbar(toolBar, false);
        toolBar.setSubtitle(title);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        adapter = new DetailAdapter(R.layout.live_item, new ArrayList<LiveDetail.ZhuboBean>());
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

    private void checkCollectState(LivePlatform platform) {
        if (platform == null) {
            ivRight.setImageResource(R.drawable.icon_un_collect);
        } else {
            ivRight.setImageResource(R.drawable.icon_collect);
        }
    }

    @Override
    public void onClick(View view) {
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
}
