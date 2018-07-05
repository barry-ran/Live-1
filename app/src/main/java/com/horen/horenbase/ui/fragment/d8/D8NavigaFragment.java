package com.horen.horenbase.ui.fragment.d8;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.horen.base.rx.BaseObserver;
import com.horen.base.ui.BaseFragment;
import com.horen.horenbase.R;
import com.horen.horenbase.api.Api;
import com.horen.horenbase.api.UrlConstant;
import com.horen.horenbase.bean.d8.NavigationTag;
import com.horen.horenbase.bean.d8.NavigitionBean;
import com.horen.horenbase.rx.RxHelper;
import com.horen.horenbase.ui.adapter.D8NavigationAdapter;
import com.horen.horenbase.utils.TagUtils;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author :ChenYangYi
 * @date :2018/07/04/16:50
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class D8NavigaFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.indexBar)
    IndexBar indexBar;
    @BindView(R.id.tvSideBarHint)
    TextView tvSideBarHint;
    private SuspensionDecoration mDecoration;
    private LinearLayoutManager layoutManager;
    private List<NavigationTag> mDatas = new ArrayList<>();
    private D8NavigationAdapter navigationAdapter;

    public static D8NavigaFragment newInstance() {
        Bundle args = new Bundle();
        D8NavigaFragment fragment = new D8NavigaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_d8_navigation;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        initRecyclerView();
        getData();
    }

    private void initRecyclerView() {
        layoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(layoutManager);
        mDecoration = new SuspensionDecoration(_mActivity, mDatas);
        recyclerView.addItemDecoration(mDecoration);
        indexBar.setmPressedShowTextView(tvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(layoutManager);//设置RecyclerView的LayoutManager
        navigationAdapter = new D8NavigationAdapter(R.layout.item_d8_navigation, mDatas);
        recyclerView.setAdapter(navigationAdapter);
    }

    /**
     * 获取导航数据
     */
    private void getData() {
        mRxManager.add(Api.getService(UrlConstant.D8_VIDEO).d8Navigation()
                .compose(RxHelper.<NavigitionBean>handleResult())
                .subscribeWith(new BaseObserver<NavigitionBean>(_mActivity, true) {
                    @Override
                    protected void _onNext(NavigitionBean bean) {
                        mDatas.addAll(TagUtils.toTagList(bean));
                        indexBar.setmSourceDatas(mDatas)//设置数据
                                .invalidate();
                        mDecoration.setmDatas(mDatas);
                        navigationAdapter.setNewData(mDatas);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                }));
    }

}
