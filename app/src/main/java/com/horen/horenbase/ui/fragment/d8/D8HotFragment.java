package com.horen.horenbase.ui.fragment.d8;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.rx.BaseObserver;
import com.horen.base.ui.BaseFragment;
import com.horen.horenbase.R;
import com.horen.horenbase.api.Api;
import com.horen.horenbase.api.UrlConstant;
import com.horen.horenbase.ui.activity.d8.D8VideoDetailActivity;
import com.horen.horenbase.bean.d8.SearchBean;
import com.horen.horenbase.bean.d8.VideoBean;
import com.horen.horenbase.rx.RxHelper;
import com.horen.horenbase.ui.adapter.SearchAdapter;
import com.horen.horenbase.utils.SnackbarUtils;
import com.horen.horenbase.utils.UniCodeUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author :ChenYangYi
 * @date :2018/07/03/08:56
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class D8HotFragment extends BaseFragment implements OnRefreshListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    private SearchAdapter searchAdapter;

    public static D8HotFragment newInstance() {
        Bundle args = new Bundle();
        D8HotFragment fragment = new D8HotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_d8_home;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 2));
        searchAdapter = new SearchAdapter(R.layout.item_search, new ArrayList<VideoBean>());
        searchAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(searchAdapter);
        refresh.setEnableLoadmore(false);
        refresh.setOnRefreshListener(this);
        searchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoBean bean = searchAdapter.getData().get(position);
                D8VideoDetailActivity.startAction(_mActivity, bean.getTitle(),
                        bean.getId_encrypt(), UniCodeUtils.replaceHttpUrl(bean.getThumb_href()));
            }
        });
        getData();
    }

    /**
     * 视频
     */
    private void getData() {
        mRxManager.add(Api.getService(UrlConstant.D8_VIDEO).d8HotVideo()
                .compose(RxHelper.<SearchBean>handleResult())
                .subscribeWith(new BaseObserver<SearchBean>(_mActivity, true) {
                    @Override
                    protected void _onNext(SearchBean search) {
                        if (search.getVideos().size() <= 0) {
                            SnackbarUtils.show(_mActivity, getString(R.string.no_data));
                            refresh.finishRefresh();
                            return;
                        }
                        searchAdapter.setNewData(search.getVideos());
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
