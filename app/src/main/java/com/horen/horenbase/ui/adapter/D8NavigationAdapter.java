package com.horen.horenbase.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.horen.horenbase.R;
import com.horen.horenbase.bean.NavigationTag;

import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/07/05/11:26
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class D8NavigationAdapter extends BaseQuickAdapter<NavigationTag, BaseViewHolder> {
    public D8NavigationAdapter(int layoutResId, @Nullable List<NavigationTag> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationTag item) {
        RecyclerView recyclerView = helper.getView(R.id.recycler_view);
        recyclerView.setLayoutManager(new FlexboxLayoutManager(mContext));
        recyclerView.setAdapter(new D8NavigationTagAdapter(R.layout.item_d8_tag, item.getItems()));
    }
}
