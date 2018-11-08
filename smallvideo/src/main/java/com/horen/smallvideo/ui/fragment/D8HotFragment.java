package com.horen.smallvideo.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.billy.cc.core.component.CC;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.app.CCName;
import com.horen.base.net.NetManager;
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
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

/**
 * @author :ChenYangYi
 * @date :2018/07/03/08:56
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class D8HotFragment extends BaseFragment implements OnRefreshListener {
    private SmartRefreshLayout refresh;
    private RecyclerView recyclerView;


    private SearchVideoAdapter searchAdapter;

    public static D8HotFragment newInstance() {
        Bundle args = new Bundle();
        D8HotFragment fragment = new D8HotFragment();
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
        searchAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setAdapter(searchAdapter);
        refresh.setEnableLoadmore(false);
        refresh.setOnRefreshListener(this);
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
        mRxManager.add(NetManager.getInstance().getVideoService().d8HotVideo()
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
