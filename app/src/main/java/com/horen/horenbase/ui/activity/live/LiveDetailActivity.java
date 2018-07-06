package com.horen.horenbase.ui.activity.live;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.rx.BaseObserver;
import com.horen.base.rx.RxSchedulers;
import com.horen.base.ui.BaseActivity;
import com.horen.horenbase.R;
import com.horen.horenbase.api.Api;
import com.horen.horenbase.api.UrlConstant;
import com.horen.horenbase.bean.live.LiveDetail;
import com.horen.horenbase.ui.adapter.DetailAdapter;
import com.horen.horenbase.utils.UniCodeUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

public class LiveDetailActivity extends BaseActivity implements OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    private DetailAdapter adapter;

    public static void startAction(Context context, String url, String title) {
        Intent intent = new Intent();
        intent.setClass(context, LiveDetailActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
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
        initToolbar(toolBar, false);
        toolBar.setSubtitle(getIntent().getStringExtra("title"));
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
                VideoActivity.startAction(mContext, zhuboBean.getAddress(), UniCodeUtils.unicodeToString(zhuboBean.getTitle()));
            }
        });
    }


    private void getData() {
        mRxManager.add(Api.getService(UrlConstant.LIVE).getDetailList(getIntent().getStringExtra("url"))
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_collect, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 收藏
            case R.id.collect:
                showShortToast("收藏");
                break;
            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
