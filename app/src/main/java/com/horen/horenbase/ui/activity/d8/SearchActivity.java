package com.horen.horenbase.ui.activity.d8;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.rx.BaseObserver;
import com.horen.base.ui.BaseActivity;
import com.horen.horenbase.R;
import com.horen.horenbase.api.Api;
import com.horen.horenbase.api.UrlConstant;
import com.horen.horenbase.bean.d8.D8VideoDetailActivity;
import com.horen.horenbase.bean.d8.SearchBean;
import com.horen.horenbase.rx.RxHelper;
import com.horen.horenbase.ui.adapter.SearchAdapter;
import com.horen.horenbase.utils.SnackbarUtils;
import com.horen.horenbase.utils.UniCodeUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author :ChenYangYi
 * @date :2018/07/03/08:56
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class SearchActivity extends BaseActivity implements OnRefreshLoadmoreListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.floating_search_view)
    FloatingSearchView floatingSearchView;
    private SearchAdapter searchAdapter;

    public int page = 1;
    public int perPage = 10;
    public String searchString = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        searchAdapter = new SearchAdapter(R.layout.item_search, new ArrayList<SearchBean.VideosBean>());
        searchAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(searchAdapter);
        refresh.setOnRefreshLoadmoreListener(this);
        searchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SearchBean.VideosBean bean = searchAdapter.getData().get(position);
                D8VideoDetailActivity.startAction(mContext, bean.getTitle(),
                        bean.getId_encrypt(), UniCodeUtils.replaceHttpUrl(bean.getThumb_href()));
            }
        });
        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                page = 1;
                searchString = newQuery;
                getData(searchString);
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
        mRxManager.add(Api.getService(UrlConstant.D8_VIDEO).d8SearchVideo(key, page, perPage)
                .compose(RxHelper.<SearchBean>handleResult())
                .subscribeWith(new BaseObserver<SearchBean>() {
                    @Override
                    protected void _onNext(SearchBean search) {
                        if (search.getVideos().size() <= 0) {
                            page--;
                            SnackbarUtils.show(SearchActivity.this, getString(R.string.no_data));
                            refresh.finishLoadmore();
                            return;
                        }
                        // 加载更多
                        if (page > 1) {
                            searchAdapter.addData(search.getVideos());
                            refresh.finishLoadmore();
                        } else {
                            searchAdapter.setNewData(search.getVideos());
                            refresh.finishRefresh();
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        page--;
                        SnackbarUtils.show(SearchActivity.this, message);
                        refresh.finishRefresh();
                        refresh.finishLoadmore();
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
