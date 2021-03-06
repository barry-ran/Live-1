package com.horen.live.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.app.CCName;
import com.horen.base.net.NetManager;
import com.horen.base.rx.BaseObserver;
import com.horen.base.rx.RxSchedulers;
import com.horen.base.ui.BaseFragment;
import com.horen.base.util.UniCodeUtils;
import com.horen.domain.live.HomeLive;
import com.horen.domain.live.HomeLive2;
import com.horen.live.R;
import com.horen.live.adapter.HomeAdapter;
import com.horen.live.ui.activity.LiveDetailActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;


public class LiveFragment extends BaseFragment implements OnRefreshListener {

    private SmartRefreshLayout refresh;
    private RecyclerView recyclerView;

    private HomeAdapter adapter;
    private int type;

    public static LiveFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        LiveFragment fragment = new LiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.live_fragment_live;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        type = getArguments().getInt("type");
        refresh = (SmartRefreshLayout) rootView.findViewById(R.id.refresh);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 3));
        adapter = new HomeAdapter(R.layout.live_item, new ArrayList<HomeLive.PingtaiBean>());
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setAdapter(adapter);
        refresh.setOnRefreshListener(this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeLive.PingtaiBean pingtaiBean = LiveFragment.this.adapter.getData().get(position);
                LiveDetailActivity.startAction(_mActivity, pingtaiBean.getAddress(),
                        UniCodeUtils.unicodeToString(pingtaiBean.getTitle()), pingtaiBean.getXinimg(), type);
            }
        });
        getData();
    }

    private void getData() {
        if (type == CCName.LIVE_1) {
            mRxManager.add(NetManager.getInstance().getLiveService().getHomeList()
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
        } else if (type == CCName.LIVE_2) {
            mRxManager.add(NetManager.getInstance().getLiveService().getLive2List()
                    .compose(RxSchedulers.<HomeLive2>io_main())
                    .subscribeWith(new BaseObserver<HomeLive2>(_mActivity, true) {
                        @Override
                        protected void _onNext(HomeLive2 homeBean) {
                            adapter.setNewData(homeBean.getData().toList());
                            refresh.finishRefresh();
                        }

                        @Override
                        protected void _onError(String message) {
                            refresh.finishRefresh();
                        }
                    }));
        }

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getData();
    }

}
