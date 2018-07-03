package com.horen.horenbase.ui.fragment;

import android.os.Bundle;

import com.horen.base.ui.BaseFragment;
import com.horen.horenbase.R;

public class D8Fragment extends BaseFragment {

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
    }
}
