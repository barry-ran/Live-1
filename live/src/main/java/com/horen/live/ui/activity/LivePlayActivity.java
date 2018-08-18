package com.horen.live.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.horen.base.util.SnackbarUtils;
import com.horen.base.util.UniCodeUtils;
import com.horen.domain.live.LiveAnchor;
import com.horen.domain.live.LiveDetail;
import com.horen.live.R;
import com.horen.live.adapter.LivePlayAdapter;
import com.horen.live.widget.VerticalViewPager;
import com.jaeger.library.StatusBarUtil;

import org.litepal.LitePal;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author :ChenYangYi
 * @date :2018/06/27/15:37
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class LivePlayActivity extends SupportActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private VerticalViewPager verticalViewPager;
    private ArrayList<LiveDetail.ZhuboBean> mData;
    private ArrayList<LiveAnchor> liveAnchors;
    private int position;


    private Toolbar toolBar;
    private TextView tvTitle;
    private AppCompatImageView ivRight;
    private LiveAnchor anchor;


    public static void startAction(Context context, ArrayList<LiveDetail.ZhuboBean> mData, ArrayList<LiveAnchor> liveAnchors, int position) {
        Intent intent = new Intent();
        intent.setClass(context, LivePlayActivity.class);
        intent.putExtra("mData", mData);
        intent.putExtra("liveAnchors", liveAnchors);
        intent.putExtra("position", position);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_activity_live_play);
        StatusBarUtil.setColor(this, Color.BLACK);
        toolBar = findViewById(R.id.tool_bar);
        tvTitle = findViewById(R.id.tv_title);
        ivRight = findViewById(R.id.iv_right);
        ivRight.setOnClickListener(this);
        verticalViewPager = (VerticalViewPager) findViewById(R.id.ultra_viewpager);
        mData = (ArrayList<LiveDetail.ZhuboBean>) getIntent().getSerializableExtra("mData");
        liveAnchors = (ArrayList<LiveAnchor>) getIntent().getSerializableExtra("liveAnchors");
        position = getIntent().getIntExtra("position", 0);
        verticalViewPager.setAdapter(new LivePlayAdapter(getSupportFragmentManager(), mData, liveAnchors));
        initToolbar(toolBar);
        // 查询数据库
        anchor = LitePal.where("url=?", mData != null ? mData.get(position).getAddress() : liveAnchors.get(position).getUrl())
                .findFirst(LiveAnchor.class);
        tvTitle.setText(UniCodeUtils.unicodeToString(mData != null ? mData.get(position).getTitle() : liveAnchors.get(position).getName()));
        checkCollectState(anchor);
        verticalViewPager.setOnPageChangeListener(this);
        verticalViewPager.setCurrentItem(position);
    }

    protected void initToolbar(@NonNull Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void checkCollectState(LiveAnchor anchor) {
        if (anchor == null) {
            ivRight.setImageResource(R.drawable.icon_un_collect);
        } else {
            ivRight.setImageResource(R.drawable.icon_collect);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        this.position = position;
        // 查询数据库
        anchor = LitePal.where("url=?", mData != null ? mData.get(position).getAddress() : liveAnchors.get(position).getUrl())
                .findFirst(LiveAnchor.class);
        tvTitle.setText(UniCodeUtils.unicodeToString(mData != null ? mData.get(position).getTitle() : liveAnchors.get(position).getName()));
        checkCollectState(anchor);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_right) {
            if (anchor == null) { // 没有收藏过
                anchor = new LiveAnchor.Builder()
                        .setUrl(mData != null ? mData.get(position).getAddress() : liveAnchors.get(position).getUrl())
                        .setImageUrl(mData != null ? mData.get(position).getImg() : liveAnchors.get(position).getImageUrl())
                        .setName(UniCodeUtils.unicodeToString(mData != null ? mData.get(position).getTitle() : liveAnchors.get(position).getName()))
                        .builder();
                if (anchor.save())
                    SnackbarUtils.show(LivePlayActivity.this, getString(R.string.collect_success));
            } else { // 已经收藏，删除收藏
                anchor.delete();
                anchor = null;
                SnackbarUtils.show(LivePlayActivity.this, getString(R.string.collect_cancle));
            }
            checkCollectState(anchor);
        }
    }
}
