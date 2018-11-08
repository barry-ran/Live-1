package com.horen.live.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.base.util.DisplayUtil;
import com.horen.base.util.GlideUtils;
import com.horen.base.util.UniCodeUtils;
import com.horen.domain.live.LiveDetail;
import com.horen.live.R;

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
        // 设置ImageView的宽高
        float width = (DisplayUtil.getScreenWidth(mContext) - 2 * DisplayUtil.dip2px(5) -  DisplayUtil.dip2px(10)) / 2f;
        ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) helper.getView(R.id.item_search_pager_group).getLayoutParams();
        layoutParams.width = (int) width;
        layoutParams.height = (int) width;
        helper.getView(R.id.item_search_pager_group).setLayoutParams(layoutParams);
        CardView.LayoutParams layoutParams1 = (CardView.LayoutParams) helper.getView(R.id.tv_title).getLayoutParams();
        layoutParams1.width = (int) width;
        helper.getView(R.id.tv_title).setLayoutParams(layoutParams1);
    }
}
