package com.horen.horenbase.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.rx.BaseObserver;
import com.horen.base.ui.BaseFragment;
import com.horen.horenbase.R;
import com.horen.horenbase.api.Api;
import com.horen.horenbase.api.UrlConstant;
import com.horen.horenbase.bean.HomeMovie;
import com.horen.horenbase.rx.RxHelper;
import com.horen.horenbase.ui.activity.live.VideoActivity;
import com.horen.horenbase.utils.ParmsUtils;
import com.horen.horenbase.utils.SnackbarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import butterknife.BindView;

public class MovieFragment extends BaseFragment implements OnRefreshLoadmoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
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
        return R.layout.fragment_live;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        movieAdapter = new HomeMovieAdapter(R.layout.item_home_movie, new ArrayList<HomeMovie.ListBean>());
        movieAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(movieAdapter);
        refresh.setOnRefreshLoadmoreListener(this);
        movieAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeMovie.ListBean bean = movieAdapter.getData().get(position);
                VideoActivity.startAction(_mActivity, bean.getPlay_urls().get(0), bean.getTitle());
            }
        });
        getData();
    }

    private void getData() {
        if (page < 1) page = 1;
        mRxManager.add(Api.getService(UrlConstant.MAOMI).getMoviceList(ParmsUtils.getMovieList(page, perPage, currentTylp))
                .compose(RxHelper.<HomeMovie>handleResult())
                .subscribeWith(new BaseObserver<HomeMovie>() {
                    @Override
                    protected void _onNext(HomeMovie movie) {
                        // 加载更多
                        if (page > 1) {
                            movieAdapter.addData(movie.getList());
                            refresh.finishLoadmore();
                        } else {
                            movieAdapter.setNewData(movie.getList());
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
