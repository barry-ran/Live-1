package com.horen.horenbase.ui.fragment;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.horenbase.R;
import com.horen.domain.HomeMovie;
import com.horen.horenbase.utils.DateUtil;
import com.horen.horenbase.utils.GlideUtils;

import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/06/29/14:48
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class HomeMovieAdapter extends BaseQuickAdapter<HomeMovie.ListBean, BaseViewHolder> {
    public HomeMovieAdapter(int layoutResId, @Nullable List<HomeMovie.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeMovie.ListBean item) {
        helper.setText(R.id.collect_text_title, item.getTitle());
        helper.setText(R.id.collect_item_text_duration, DateUtil.translateTime(Integer.valueOf(item.getDuration())));
        helper.setVisible(R.id.collect_item_text_store, false);
        GlideUtils.load(mContext, item.getCover(), (ImageView) helper.getView(R.id.collect_item_image));
    }
}
