package com.horen.live.ui.activity;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.ui.BaseActivity;
import com.horen.domain.live.LiveAnchor;
import com.horen.live.R;
import com.horen.live.adapter.LiveCollectAnchorAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;


/**
 * @author :ChenYangYi
 * @date :2018/07/06/14:31
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class LiveAnchorCollectActivity extends BaseActivity implements OnRefreshListener {
    private SmartRefreshLayout refresh;
    private RecyclerView recyclerView;
    private Toolbar toolBar;
    private TextView tvTitle;
    private AppCompatImageView ivRight;
    private LiveCollectAnchorAdapter collectAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.live_activity_live_collect;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        refresh = (SmartRefreshLayout) findViewById(R.id.refresh);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        toolBar = (Toolbar) findViewById(R.id.tool_bar);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivRight = (AppCompatImageView) findViewById(R.id.iv_right);
        initToolbar(toolBar, false);
        tvTitle.setText("收藏主播");
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        collectAdapter = new LiveCollectAnchorAdapter(R.layout.live_item, new ArrayList<LiveAnchor>());
        recyclerView.setAdapter(collectAdapter);
        List<LiveAnchor> platforms = LitePal.findAll(LiveAnchor.class);
        collectAdapter.setNewData(platforms);
        collectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LiveAnchor platform = collectAdapter.getData().get(position);
                VideoActivity.startAction(mContext, platform.getUrl(),
                        platform.getName(), platform.getImageUrl());
            }
        });

        refresh.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        List<LiveAnchor> platforms = LitePal.findAll(LiveAnchor.class);
        collectAdapter.setNewData(platforms);
        refresh.finishRefresh();
    }
}
