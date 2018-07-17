package com.horen.smallvideo.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.allen.library.SuperButton;
import com.billy.cc.core.component.CC;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.base.app.CCName;
import com.horen.base.util.UniCodeUtils;
import com.horen.domain.d8.NavigationTag;
import com.horen.smallvideo.R;

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
                CC.obtainBuilder(CCName.SMALL_VIDEO)
                        .setActionName(CCName.TAG_VIDEO)
                        .addParam("tag_name", UniCodeUtils.unicodeToString(item.getName()))
                        .build()
                        .callAsync();
            }
        });
    }
}
