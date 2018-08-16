package com.horen.live.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.horen.domain.live.LiveDetail;
import com.horen.live.ui.fragment.LivePlayerFragment;

import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/08/16/15:00
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class LivePlayAdapter extends FragmentStatePagerAdapter {

    private List<LiveDetail.ZhuboBean> mData;

    public LivePlayAdapter(FragmentManager fm, List<LiveDetail.ZhuboBean> mData) {
        super(fm);
        this.mData = mData;
    }

    @Override
    public Fragment getItem(int position) {
        return LivePlayerFragment.newInstance(mData.get(position).getAddress());
    }

    @Override
    public int getCount() {
        return mData.size();
    }
}
