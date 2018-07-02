package com.horen.horenbase.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.rx.BaseObserver;
import com.horen.base.rx.RxSchedulers;
import com.horen.base.ui.BaseFragment;
import com.horen.horenbase.R;
import com.horen.horenbase.api.Api;
import com.horen.horenbase.api.UrlConstant;
import com.horen.horenbase.bean.HomeSearch;
import com.horen.horenbase.bean.SearchDetail;
import com.horen.horenbase.ui.activity.live.VideoActivity;
import com.horen.horenbase.ui.adapter.SearchAdapter;
import com.horen.horenbase.utils.SnackbarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import butterknife.BindView;

public class SearchFragment extends BaseFragment implements OnRefreshLoadmoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.floating_search_view)
    FloatingSearchView floatingSearchView;
    private SearchAdapter movieAdapter;

    public int page = 1;
    public int perPage = 20;
    public String searchString = "";

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_search;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        movieAdapter = new SearchAdapter(R.layout.item, new ArrayList<HomeSearch.DataBean>());
        movieAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(movieAdapter);
        refresh.setOnRefreshLoadmoreListener(this);
        movieAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeSearch.DataBean bean = movieAdapter.getData().get(position);
                getVideoDetail(bean.getUrl2(), bean.getTitel());
            }
        });
        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                page = 1;
                searchString = newQuery;
                getData(newQuery);
            }
        });
    }

    /**
     * 视频
     *
     * @param key
     */
    private void getData(String key) {
        if (TextUtils.isEmpty(key)) return;
        if (page < 1) page = 1;
        mRxManager.add(Api.getService(UrlConstant.LANG_YA).searchVideo(key, page)
                .compose(RxSchedulers.<HomeSearch>io_main())
                .subscribeWith(new BaseObserver<HomeSearch>() {
                    @Override
                    protected void _onNext(HomeSearch search) {
                        if (search.getData().size() <= 0) {
                            page--;
                            SnackbarUtils.show(_mActivity, getString(R.string.no_data));
                            return;
                        }
                        search.getData().remove(0); // 去除第一条公告数据
                        // 加载更多
                        if (page > 1) {
                            movieAdapter.addData(search.getData());
                            refresh.finishLoadmore();
                        } else {
                            movieAdapter.setNewData(search.getData());
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

    /**
     * 视频详情
     *
     * @param url
     * @param title
     */
    private void getVideoDetail(String url, final String title) {
        mRxManager.add(Api.getService(UrlConstant.LANG_YA_DETAIL).videoDetail(url)
                .compose(RxSchedulers.<SearchDetail>io_main())
                .subscribeWith(new BaseObserver<SearchDetail>() {
                    @Override
                    protected void _onNext(SearchDetail searchDetail) {
                        if (searchDetail.get_$480p() == null) {
                            SnackbarUtils.show(_mActivity, "暂无资源");
                        } else {
                            VideoActivity.startAction(_mActivity, searchDetail.get_$360p(), title);
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        showShortToast(message);
                    }
                }));
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        getData(searchString);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        getData(searchString);
    }


}
