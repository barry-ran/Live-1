package com.horen.horenbase.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.horen.base.ui.BaseFragment;
import com.horen.horenbase.R;
import com.horen.horenbase.base.BaseFragmentAdapter;
import com.horen.horenbase.ui.fragment.d8.D8HomeFragment;
import com.horen.horenbase.ui.fragment.d8.D8HotFragment;
import com.horen.horenbase.ui.fragment.d8.D8NavigaFragment;
import com.horen.horenbase.widget.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public class D8Fragment extends BaseFragment {

    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private static final String[] Titles = new String[]{"首页", "热门", "标签"};
    private List<SupportFragment> mFragments = new ArrayList<>();

    public static D8Fragment newInstance() {
        Bundle args = new Bundle();
        D8Fragment fragment = new D8Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_d8;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        initViewPager();
        initMagicIndicator();
        viewPager.setOffscreenPageLimit(Titles.length);
    }

    private void initMagicIndicator() {
        magicIndicator.setBackgroundColor(ContextCompat.getColor(_mActivity, R.color.white));
        CommonNavigator commonNavigator = new CommonNavigator(_mActivity);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return Titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(Titles[index]);
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setNormalColor(Color.GRAY);
                simplePagerTitleView.setSelectedColor(Color.BLACK);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                BezierPagerIndicator indicator = new BezierPagerIndicator(context);
                indicator.setColors(Color.parseColor("#ff4a42"), Color.parseColor("#fcde64"), Color.parseColor("#73e8f4"), Color.parseColor("#76b0ff"), Color.parseColor("#c683fe"));
                return indicator;
            }
        });
        commonNavigator.setAdjustMode(true);
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    private void initViewPager() {
        mFragments.add(D8HomeFragment.newInstance());
        mFragments.add(D8HotFragment.newInstance());
        mFragments.add(D8NavigaFragment.newInstance());
        // 初始化Viewpager
        viewPager.setAdapter(new BaseFragmentAdapter(getChildFragmentManager(), mFragments, Titles));
    }

}
