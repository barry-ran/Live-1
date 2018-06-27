package com.horen.horenbase;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.horenbase.bean.HomeBean;

import java.util.List;

/**
 * @author :ChenY\angYi
 * @date :2018/06/27/13:22
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class HomeAdapter extends BaseQuickAdapter<HomeBean.PingtaiBean, BaseViewHolder> {
    public HomeAdapter(int layoutResId, @Nullable List<HomeBean.PingtaiBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.PingtaiBean item) {
        GlideUtils.loadAll(mContext, item.getXinimg(), (ImageView) helper.getView(R.id.iv));
        helper.setText(R.id.tv_title, UniCodeUtils.unicodeToString(item.getTitle()));
    }
}
