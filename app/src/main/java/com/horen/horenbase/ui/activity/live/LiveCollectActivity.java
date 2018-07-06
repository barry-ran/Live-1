package com.horen.horenbase.ui.activity.live;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.ui.BaseActivity;
import com.horen.horenbase.R;
import com.horen.horenbase.bean.live.LivePlatform;
import com.horen.horenbase.ui.adapter.LiveCollectAdapter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author :ChenYangYi
 * @date :2018/07/06/14:31
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class LiveCollectActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    AppCompatImageView ivRight;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private LiveCollectAdapter collectAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_live_collect;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initToolbar(toolBar, false);
        tvTitle.setText("收藏平台");
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        collectAdapter = new LiveCollectAdapter(R.layout.item, new ArrayList<LivePlatform>());
        recyclerView.setAdapter(collectAdapter);
        List<LivePlatform> platforms = LitePal.findAll(LivePlatform.class);
        collectAdapter.setNewData(platforms);
        collectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LivePlatform platform = collectAdapter.getData().get(position);
                LiveDetailActivity.startAction(mContext, platform.getUrl(),
                        platform.getName(), platform.getImageUrl());
            }
        });
    }

}
