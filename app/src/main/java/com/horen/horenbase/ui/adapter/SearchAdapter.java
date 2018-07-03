package com.horen.horenbase.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.horenbase.R;
import com.horen.horenbase.bean.d8.SearchBean;
import com.horen.horenbase.utils.GlideUtils;
import com.horen.horenbase.utils.UniCodeUtils;

import java.util.List;

/**
 * @author :ChenY\angYi
 * @date :2018/06/27/13:22
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class SearchAdapter extends BaseQuickAdapter<SearchBean.VideosBean, BaseViewHolder> {
    public SearchAdapter(int layoutResId, @Nullable List<SearchBean.VideosBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchBean.VideosBean item) {
        helper.setText(R.id.collect_text_title, UniCodeUtils.unicodeToString(item.getTitle()));
        helper.setText(R.id.collect_item_text_duration, item.getDuration());
        helper.setText(R.id.tv_upload_time, UniCodeUtils.unicodeToString(item.getTimeout()));
        helper.setText(R.id.collect_item_text_store, item.getPlay_count() + "");
        GlideUtils.load(mContext,
                UniCodeUtils.replaceHttpUrl(item.getThumb_href()), (ImageView) helper.getView(R.id.collect_item_image));
    }
}
