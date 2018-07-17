package com.horen.horenbase.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.allen.library.SuperButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.horenbase.R;
import com.horen.domain.d8.NavigationTag;
import com.horen.horenbase.ui.activity.d8.D8TagVideoActivity;
import com.horen.base.util.UniCodeUtils;

import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/07/05/11:29
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class D8NavigationTagAdapter extends BaseQuickAdapter<NavigationTag.ItemsBean, BaseViewHolder> {
    public D8NavigationTagAdapter(int layoutResId, @Nullable List<NavigationTag.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final NavigationTag.ItemsBean item) {
        SuperButton superButton = helper.getView(R.id.sbt_tag);
        superButton.setText(UniCodeUtils.unicodeToString(item.getName()));
        superButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                D8TagVideoActivity.startAction(mContext, UniCodeUtils.unicodeToString(item.getName()));
            }
        });
    }
}
