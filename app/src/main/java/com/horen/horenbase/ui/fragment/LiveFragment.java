package com.horen.horenbase.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.rx.BaseObserver;
import com.horen.base.rx.RxSchedulers;
import com.horen.base.ui.BaseFragment;
import com.horen.horenbase.R;
import com.horen.horenbase.api.Api;
import com.horen.horenbase.api.UrlConstant;
import com.horen.horenbase.bean.live.HomeLive;
import com.horen.horenbase.ui.activity.live.LiveDetailActivity;
import com.horen.horenbase.ui.adapter.HomeAdapter;
import com.horen.horenbase.utils.UniCodeUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

public class LiveFragment extends BaseFragment implements OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    private HomeAdapter adapter;

    public static LiveFragment newInstance() {
        Bundle args = new Bundle();
        LiveFragment fragment = new LiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_live;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 3));
        adapter = new HomeAdapter(R.layout.item, new ArrayList<HomeLive.PingtaiBean>());
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(adapter);
        refresh.setOnRefreshListener(this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeLive.PingtaiBean pingtaiBean = LiveFragment.this.adapter.getData().get(position);
                LiveDetailActivity.startAction(_mActivity, pingtaiBean.getAddress(),
                        UniCodeUtils.unicodeToString(pingtaiBean.getTitle()));
            }
        });
        getData();
    }

    private void getData() {
        mRxManager.add(Api.getService(UrlConstant.LIVE).getHomeList()
                .compose(RxSchedulers.<HomeLive>io_main())
                .subscribeWith(new BaseObserver<HomeLive>(_mActivity, true) {
                    @Override
                    protected void _onNext(HomeLive homeBean) {
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
