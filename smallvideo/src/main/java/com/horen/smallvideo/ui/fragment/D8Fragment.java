package com.horen.smallvideo.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.horen.base.ui.BaseFragment;
import com.horen.base.ui.BaseFragmentAdapter;
import com.horen.smallvideo.R;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public class D8Fragment extends BaseFragment {
    private ViewPager viewPager;

    private static final String[] Titles = new String[]{"首页", "热门", "标签"};
    private List<SupportFragment> mFragments = new ArrayList<>();
    private TabLayout mTabLayout;

    public static D8Fragment newInstance() {
        Bundle args = new Bundle();
        D8Fragment fragment = new D8Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.smallvideo_fragment_d8;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        mTabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        initViewPager();
        initMagicIndicator();
        viewPager.setOffscreenPageLimit(Titles.length);
    }

    private void initMagicIndicator() {
    }

    private void initViewPager() {
        mFragments.add(D8HomeFragment.newInstance());
        mFragments.add(D8HotFragment.newInstance());
        mFragments.add(D8NavigaFragment.newInstance());
        mTabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new BaseFragmentAdapter(getChildFragmentManager(), mFragments, Titles));
    }
}
