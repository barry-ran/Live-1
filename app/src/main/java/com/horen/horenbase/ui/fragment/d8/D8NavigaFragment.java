package com.horen.horenbase.ui.fragment.d8;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.horen.base.rx.BaseObserver;
import com.horen.base.ui.BaseFragment;
import com.horen.horenbase.R;
import com.horen.horenbase.api.Api;
import com.horen.horenbase.api.UrlConstant;
import com.horen.horenbase.bean.d8.NavigationTag;
import com.horen.horenbase.bean.d8.NavigitionBean;
import com.horen.horenbase.rx.RxHelper;
import com.horen.horenbase.utils.TagUtils;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;

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
        getData();
    }

    /**
     * 获取导航数据
     */
    private void getData() {
        mRxManager.add(Api.getService(UrlConstant.D8_VIDEO).d8Navigation()
                .compose(RxHelper.<NavigitionBean>handleResult())
                .subscribeWith(new BaseObserver<NavigitionBean>() {
                    @Override
                    protected void _onNext(NavigitionBean bean) {
                        List<NavigationTag> tags = TagUtils.toTagList(bean);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                }));
    }

}
