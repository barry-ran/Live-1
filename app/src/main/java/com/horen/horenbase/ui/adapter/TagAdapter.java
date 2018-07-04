package com.horen.horenbase.ui.adapter;

import android.support.annotation.Nullable;

import com.allen.library.SuperButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.horenbase.R;
import com.horen.horenbase.bean.d8.VideoDetail;
import com.horen.horenbase.utils.UniCodeUtils;

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
    protected void convert(BaseViewHolder helper, VideoDetail.VideoBean.TagsBean item) {
        SuperButton superButton = helper.getView(R.id.sbt_tag);
        superButton.setText(UniCodeUtils.unicodeToString(item.getName()));
    }
}