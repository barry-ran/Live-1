package com.horen.live.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.horen.domain.live.LiveDetail;
import com.horen.live.R;
import com.horen.live.adapter.LivePlayAdapter;
import com.jaeger.library.StatusBarUtil;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author :ChenYangYi
 * @date :2018/06/27/15:37
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class LivePlayActivity extends SupportActivity {
    private UltraViewPager ultraViewPager;
    private ArrayList<LiveDetail.ZhuboBean> mData;
    private int position;

    public static void startAction(Context context, ArrayList<LiveDetail.ZhuboBean> mData, int position) {
        Intent intent = new Intent();
        intent.setClass(context, LivePlayActivity.class);
        intent.putExtra("mData", mData);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_activity_live_play);
        StatusBarUtil.setColor(this, Color.BLACK);
        mData = (ArrayList<LiveDetail.ZhuboBean>) getIntent().getSerializableExtra("mData");
        position = getIntent().getIntExtra("position", 0);
        ultraViewPager = findViewById(R.id.ultra_viewpager);
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.VERTICAL);
        ultraViewPager.setAdapter(new LivePlayAdapter(getSupportFragmentManager(), mData));
        ultraViewPager.setCurrentItem(position);
    }

}
