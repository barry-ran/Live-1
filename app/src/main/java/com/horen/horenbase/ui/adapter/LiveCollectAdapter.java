package com.horen.horenbase.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.horenbase.R;
import com.horen.horenbase.bean.LivePlatform;
import com.horen.horenbase.utils.GlideUtils;
import com.horen.horenbase.utils.UniCodeUtils;

import java.util.List;

/**
 * @author :ChenY\angYi
 * @date :2018/06/27/13:22
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class LiveCollectAdapter extends BaseQuickAdapter<LivePlatform, BaseViewHolder> {
    public LiveCollectAdapter(int layoutResId, @Nullable List<LivePlatform> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LivePlatform item) {
        GlideUtils.load(mContext, item.getImageUrl(), (ImageView) helper.getView(R.id.iv));
        helper.setText(R.id.tv_title, UniCodeUtils.unicodeToString(item.getName()));
    }
}
