package com.horen.smallvideo.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.allen.library.SuperButton;
import com.billy.cc.core.component.CC;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.base.app.CCName;
import com.horen.base.util.UniCodeUtils;
import com.horen.domain.d8.VideoDetail;
import com.horen.smallvideo.R;

import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/07/03/15:27
 * @description :标签tag
 * @github :https://github.com/chenyy0708
 */
public class VideoTagAdapter extends BaseQuickAdapter<VideoDetail.VideoBean.TagsBean, BaseViewHolder> {

    public VideoTagAdapter(int layoutResId, @Nullable List<VideoDetail.VideoBean.TagsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final VideoDetail.VideoBean.TagsBean item) {
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
