package com.horen.horenbase.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.rx.BaseObserver;
import com.horen.base.rx.RxSchedulers;
import com.horen.base.ui.BaseActivity;
import com.horen.horenbase.R;
import com.horen.horenbase.api.Api;
import com.horen.horenbase.bean.HomeBean;
import com.horen.horenbase.ui.adapter.HomeAdapter;
import com.horen.horenbase.utils.UniCodeUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    private HomeAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        initToolbar(toolBar, false);
        toolBar.setSubtitle("直播");
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        adapter = new HomeAdapter(R.layout.item, new ArrayList<HomeBean.PingtaiBean>());
        recyclerView.setAdapter(adapter);
        refresh.setOnRefreshListener(this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeBean.PingtaiBean pingtaiBean = MainActivity.this.adapter.getData().get(position);
                DetailActivity.startAction(mContext, pingtaiBean.getAddress(),
                        UniCodeUtils.unicodeToString(pingtaiBean.getTitle()));
            }
        });
        getData();
    }

    private void getData() {
        mRxManager.add(Api.getDefult().getHomeList()
                .compose(RxSchedulers.<HomeBean>io_main())
                .subscribeWith(new BaseObserver<HomeBean>(mContext, false) {
                    @Override
                    protected void _onNext(HomeBean homeBean) {
                        adapter.setNewData(homeBean.getPingtai());
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

}
