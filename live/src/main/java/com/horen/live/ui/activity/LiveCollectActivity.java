package com.horen.live.ui.activity;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.ui.BaseActivity;
import com.horen.domain.live.LivePlatform;
import com.horen.live.R;
import com.horen.live.adapter.LiveCollectAdapter;
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
public class LiveCollectActivity extends BaseActivity implements OnRefreshListener, View.OnClickListener {
    private SmartRefreshLayout refresh;
    private RecyclerView recyclerView;
    private Toolbar toolBar;
    private AppCompatImageView ivRight;
    private LiveCollectAdapter collectAdapter;

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
        ivRight = (AppCompatImageView) findViewById(R.id.iv_right);
        ivRight.setOnClickListener(this);
        initToolbar(toolBar, false);
        toolBar.setTitle("收藏平台");
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        collectAdapter = new LiveCollectAdapter(R.layout.live_item, new ArrayList<LivePlatform>());
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
        refresh.setOnRefreshListener(this);
        ivRight.setImageResource(R.drawable.icon_anchor);
        ivRight.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        List<LivePlatform> platforms = LitePal.findAll(LivePlatform.class);
        collectAdapter.setNewData(platforms);
        refresh.finishRefresh();
    }

    @Override
    public void onClick(View view) {
        startActivity(LiveAnchorCollectActivity.class);
    }
}
