package com.horen.movie.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.billy.cc.core.component.CC;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.app.CCName;
import com.horen.base.net.Api;
import com.horen.base.net.UrlConstant;
import com.horen.base.rx.BaseObserver;
import com.horen.base.rx.RxSchedulers;
import com.horen.base.ui.BaseFragment;
import com.horen.base.util.ParmsUtils;
import com.horen.base.util.SnackbarUtils;
import com.horen.domain.HomeMovie;
import com.horen.domain.d8.BaseEntry;
import com.horen.movie.R;
import com.horen.movie.adapter.HomeMovieAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

public class MovieFragment extends BaseFragment implements OnRefreshLoadmoreListener {
    private SmartRefreshLayout refresh;
    private RecyclerView recyclerView;

    private HomeMovieAdapter movieAdapter;

    public int page = 1;
    public int perPage = 20;
    public int currentTylp = 1;

    public static MovieFragment newInstance() {
        Bundle args = new Bundle();
        MovieFragment fragment = new MovieFragment();
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

        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        movieAdapter = new HomeMovieAdapter(R.layout.movie_item_home_movie, new ArrayList<HomeMovie.ListBean>());
        movieAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(movieAdapter);
        refresh.setOnRefreshLoadmoreListener(this);
        movieAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeMovie.ListBean bean = movieAdapter.getData().get(position);
                CC.obtainBuilder(CCName.LIVE)
                        .setActionName(CCName.VIDEO_PLAY)
                        .addParam("url", bean.getPlay_urls().get(0))
                        .addParam("title", bean.getTitle())
                        .addParam("imageUrl", "")
                        .build()
                        .callAsync();
            }
        });
        getData();
    }

    private void getData() {
        if (page < 1) page = 1;
        mRxManager.add(Api.getService(UrlConstant.MAO_MI).getMoviceList(ParmsUtils.getMovieList(page, perPage, currentTylp))
                .compose(RxSchedulers.<BaseEntry<HomeMovie>>io_main())
                .subscribeWith(new BaseObserver<BaseEntry<HomeMovie>>(_mActivity, true) {
                    @Override
                    protected void _onNext(BaseEntry<HomeMovie> entry) {
                        // 加载更多
                        if (page > 1) {
                            movieAdapter.addData(entry.getData().getList());
                            refresh.finishLoadmore();
                        } else {
                            movieAdapter.setNewData(entry.getData().getList());
                            refresh.finishRefresh();
                        }
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
