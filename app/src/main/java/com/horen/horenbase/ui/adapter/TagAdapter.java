package com.horen.horenbase.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.allen.library.SuperButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.horenbase.R;
import com.horen.domain.d8.VideoDetail;
import com.horen.horenbase.ui.activity.d8.D8TagVideoActivity;
import com.horen.base.util.UniCodeUtils;

import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/07/03/15:27
 * @description :标签tag
 * @github :https://github.com/chenyy0708
 */
public class TagAdapter extends BaseQuickAdapter<VideoDetail.VideoBean.TagsBean, BaseViewHolder> {

    public TagAdapter(int layoutResId, @Nullable List<VideoDetail.VideoBean.TagsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final VideoDetail.VideoBean.TagsBean item) {
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
