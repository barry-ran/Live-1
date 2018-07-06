package com.horen.horenbase.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.horenbase.R;
import com.horen.horenbase.bean.live.LiveDetail;
import com.horen.horenbase.utils.GlideUtils;
import com.horen.horenbase.utils.UniCodeUtils;

import java.util.List;

/**
 * @author :ChenY\angYi
 * @date :2018/06/27/13:22
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class DetailAdapter extends BaseQuickAdapter<LiveDetail.ZhuboBean, BaseViewHolder> {
    public DetailAdapter(int layoutResId, @Nullable List<LiveDetail.ZhuboBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveDetail.ZhuboBean item) {
        GlideUtils.load(mContext, item.getImg(), (ImageView) helper.getView(R.id.iv));
        helper.setText(R.id.tv_title, UniCodeUtils.unicodeToString(item.getTitle()));
    }
}
