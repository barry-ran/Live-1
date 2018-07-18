package com.horen.movie.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.net.NetManager;
import com.horen.base.rx.BaseObserver;
import com.horen.base.rx.RxSchedulers;
import com.horen.base.ui.BaseFragment;
import com.horen.base.util.SnackbarUtils;
import com.horen.domain.sd.SDLiveList;
import com.horen.domain.sd.SDResponse;
import com.horen.movie.R;
import com.horen.movie.adapter.SdLiveAdapter;
import com.horen.movie.utils.AESUtil;
import com.horen.movie.utils.SDJsonUtil;
import com.horen.movie.utils.SDParmrsUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SDFragment extends BaseFragment implements OnRefreshLoadmoreListener {
    private SmartRefreshLayout refresh;
    private RecyclerView recyclerView;

    private SdLiveAdapter movieAdapter;

    public int page = 1;
    public int perPage = 20;

    public static SDFragment newInstance() {
        Bundle args = new Bundle();
        SDFragment fragment = new SDFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.movie_fragment_live;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        refresh = (SmartRefreshLayout) rootView.findViewById(R.id.refresh);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        refresh.setEnableLoadmore(false);
        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 2));
        movieAdapter = new SdLiveAdapter(R.layout.movie_item_home_movie, new ArrayList<SDLiveList.ListBean>());
        movieAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(movieAdapter);
        refresh.setOnRefreshLoadmoreListener(this);
        movieAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SDLiveList.ListBean bean = movieAdapter.getData().get(position);
                mRxManager.add(NetManager.getInstance().getSdService().getLiveUrl(SDParmrsUtils.getLiveUrl(bean.getRoom_id()), "1", "video", "get_video2")
                        .compose(RxSchedulers.<SDResponse>io_main())
                        .subscribeWith(new BaseObserver<SDResponse>(_mActivity, true) {
                            @Override
                            protected void _onNext(SDResponse sdLiveList) {
                                String s = AESUtil.decrypt(sdLiveList.getOutput());
                                System.out.println(s);
                            }

                            @Override
                            protected void _onError(String message) {
                            }
                        }));
            }
        });
        getData();
    }

    private void getData() {
        if (page < 1) page = 1;
        mRxManager.add(NetManager.getInstance().getSdService().getAllLive()
                .compose(RxSchedulers.<SDLiveList>io_main())
                .subscribeWith(new BaseObserver<SDLiveList>(_mActivity, true) {
                    @Override
                    protected void _onNext(SDLiveList sdLiveList) {
                        sdLiveList.getList().remove(0); // 移除广告位
                        movieAdapter.setNewData(sdLiveList.getList());
                        refresh.finishRefresh();
                    }

                    @Override
                    protected void _onError(String message) {
                        page--;
                        SnackbarUtils.show(_mActivity, message);
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
