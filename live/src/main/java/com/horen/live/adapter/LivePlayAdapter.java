package com.horen.live.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.horen.domain.live.LiveAnchor;
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
    /**
     * 在线播放地址
     */
    private List<LiveDetail.ZhuboBean> mData;
    /**
     * 收藏地址
     */
    private List<LiveAnchor> liveAnchors;

    public LivePlayAdapter(FragmentManager fm, List<LiveDetail.ZhuboBean> mData, List<LiveAnchor> liveAnchors) {
        super(fm);
        this.mData = mData;
        this.liveAnchors = liveAnchors;
    }

    @Override
    public Fragment getItem(int position) {
        return LivePlayerFragment.newInstance(mData != null ? mData.get(position).getAddress() : liveAnchors.get(position).getUrl());
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : liveAnchors.size();
    }
}
