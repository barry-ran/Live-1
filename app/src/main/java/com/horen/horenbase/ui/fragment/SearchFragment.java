package com.horen.horenbase.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.horen.base.ui.BaseFragment;
import com.horen.horenbase.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;

public class SearchFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.floating_search_view)
    FloatingSearchView floatingSearchView;

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_search;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
    }
}
