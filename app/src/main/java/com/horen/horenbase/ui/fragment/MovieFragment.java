package com.horen.horenbase.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.rx.BaseObserver;
import com.horen.base.ui.BaseFragment;
import com.horen.horenbase.R;
import com.horen.horenbase.api.Api;
import com.horen.horenbase.bean.HomeBean;
import com.horen.horenbase.rx.RxHelper;
import com.horen.horenbase.ui.adapter.HomeAdapter;
import com.horen.horenbase.utils.SnackbarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

public class MovieFragment extends BaseFragment implements OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    private HomeAdapter adapter;

    public static MovieFragment newInstance() {
        Bundle args = new Bundle();
        MovieFragment fragment = new MovieFragment();
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
        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 2));
        adapter = new HomeAdapter(R.layout.item, new ArrayList<HomeBean.PingtaiBean>());
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(adapter);
        refresh.setOnRefreshListener(this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        getData();
    }

    private void getData() {
        mRxManager.add(Api.getMovie().getMoviceList()
                .compose(RxHelper.handleResult())
                .subscribeWith(new BaseObserver<Object>() {
                    @Override
                    protected void _onNext(Object s) {
                        refresh.finishRefresh();
                    }

                    @Override
                    protected void _onError(String message) {
                        SnackbarUtils.show(_mActivity, message);
                        refresh.finishRefresh();
                    }
                }));

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getData();
    }

}
