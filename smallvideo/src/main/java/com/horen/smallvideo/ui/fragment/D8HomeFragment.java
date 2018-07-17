package com.horen.smallvideo.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.billy.cc.core.component.CC;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.app.CCName;
import com.horen.base.net.Api;
import com.horen.base.net.UrlConstant;
import com.horen.base.rx.BaseObserver;
import com.horen.base.rx.RxHelper;
import com.horen.base.ui.BaseFragment;
import com.horen.base.util.SnackbarUtils;
import com.horen.base.util.UniCodeUtils;
import com.horen.domain.d8.SearchBean;
import com.horen.domain.d8.VideoBean;
import com.horen.smallvideo.R;
import com.horen.smallvideo.adapter.SearchVideoAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

/**
 * @author :ChenYangYi
 * @date :2018/07/03/08:56
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class D8HomeFragment extends BaseFragment implements OnRefreshLoadmoreListener {
    private SmartRefreshLayout refresh;
    private RecyclerView recyclerView;
    public int page = 1;
    public int perPage = 10;
    private SearchVideoAdapter searchAdapter;

    public static D8HomeFragment newInstance() {
        Bundle args = new Bundle();
        D8HomeFragment fragment = new D8HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.smallvideo_fragment_d8_home;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        refresh = (SmartRefreshLayout) rootView.findViewById(R.id.refresh);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 2));
        searchAdapter = new SearchVideoAdapter(R.layout.smallvideo_item_search, new ArrayList<VideoBean>());
        searchAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(searchAdapter);
        refresh.setOnRefreshLoadmoreListener(this);
        searchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoBean bean = searchAdapter.getData().get(position);
                CC.obtainBuilder(CCName.SMALL_VIDEO)
                        .setActionName(CCName.SMALL_VIDEO_DETAIL)
                        .addParam("title", bean.getTitle())
                        .addParam("url", bean.getId_encrypt())
                        .addParam("imageUrl", UniCodeUtils.replaceHttpUrl(bean.getThumb_href()))
                        .build()
                        .callAsync();
            }
        });
        getData();
    }

    /**
     * 视频
     */
    private void getData() {
        if (page < 1) page = 1;
        mRxManager.add(Api.getService(UrlConstant.D8_VIDEO).d8HomeVideo(page, perPage)
                .compose(RxHelper.<SearchBean>handleResult())
                .subscribeWith(new BaseObserver<SearchBean>(_mActivity, true) {
                    @Override
                    protected void _onNext(SearchBean search) {
                        if (search.getVideos().size() <= 0) {
                            page--;
                            SnackbarUtils.show(_mActivity, getString(R.string.no_data));
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
