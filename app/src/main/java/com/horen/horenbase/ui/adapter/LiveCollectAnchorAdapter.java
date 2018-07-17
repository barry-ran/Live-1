package com.horen.horenbase.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.horenbase.R;
import com.horen.domain.live.LiveAnchor;
import com.horen.base.util.GlideUtils;
import com.horen.base.util.UniCodeUtils;

import java.util.List;

/**
 * @author :ChenY\angYi
 * @date :2018/06/27/13:22
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class LiveCollectAnchorAdapter extends BaseQuickAdapter<LiveAnchor, BaseViewHolder> {
    public LiveCollectAnchorAdapter(int layoutResId, @Nullable List<LiveAnchor> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveAnchor item) {
        GlideUtils.load(mContext, item.getImageUrl(), (ImageView) helper.getView(R.id.iv));
        helper.setText(R.id.tv_title, UniCodeUtils.unicodeToString(item.getName()));
    }
}
