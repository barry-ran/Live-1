package com.horen.live.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.horen.base.util.GlideUtils;
import com.horen.domain.live.LiveAnchor;
import com.horen.domain.live.LiveDetail;
import com.horen.live.R;

import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/08/16/15:00
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class NewLivePlayAdapter extends PagerAdapter {
    /**
     * 在线播放地址
     */
    private List<LiveDetail.ZhuboBean> mData;
    /**
     * 收藏地址
     */
    private List<LiveAnchor> liveAnchors;

    private Context mContext;

    public NewLivePlayAdapter(Context mContext, List<LiveDetail.ZhuboBean> mData, List<LiveAnchor> liveAnchors) {
        this.mData = mData;
        this.liveAnchors = liveAnchors;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.live_view_room_item, null);
        view.setId(position);
        ImageView imageView = view.findViewById(R.id.anchor_img);
        // 主播图片
        GlideUtils.load(mContext, mData != null ? mData.get(position).getImg() : liveAnchors.get(position).getImageUrl(), imageView);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : liveAnchors.size();
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(container.findViewById(position));
    }
}
