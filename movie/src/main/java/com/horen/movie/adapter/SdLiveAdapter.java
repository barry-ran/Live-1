package com.horen.movie.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.base.util.GlideUtils;
import com.horen.base.util.UniCodeUtils;
import com.horen.domain.sd.SDLiveList;
import com.horen.movie.R;

import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/06/29/14:48
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class SdLiveAdapter extends BaseQuickAdapter<SDLiveList.ListBean, BaseViewHolder> {
    public SdLiveAdapter(int layoutResId, @Nullable List<SDLiveList.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SDLiveList.ListBean item) {
        helper.setText(R.id.collect_text_title, item.getIs_live_pay().equals("0") ? UniCodeUtils.unicodeToString(item.getTitle()) + "(免费)" :
                UniCodeUtils.unicodeToString(item.getNick_name()));

        helper.setText(R.id.collect_item_text_duration, UniCodeUtils.unicodeToString(item.getNick_name()));
        helper.setVisible(R.id.collect_item_text_store, false);
        GlideUtils.load(mContext, UniCodeUtils.replaceHttpUrl(item.getLive_image()), (ImageView) helper.getView(R.id.collect_item_image));
    }
}
