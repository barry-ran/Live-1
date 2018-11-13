package com.horen.live;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.billy.cc.core.component.CC;
import com.horen.base.app.CCName;
import com.horen.live.ui.fragment.LiveFragment;

import me.yokeyword.fragmentation.SupportActivity;


/**
 * @author :ChenYangYi
 * @date :2018/07/17/17:05
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class LaunerActivity extends SupportActivity implements View.OnClickListener {
    private FrameLayout flContiner;


    private Toolbar toolBar;
    private AppCompatImageView ivRight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_activity_launcher);
        flContiner = findViewById(R.id.fl_continer);
        loadRootFragment(R.id.fl_continer, LiveFragment.newInstance());

        toolBar = (Toolbar) findViewById(R.id.tool_bar);
        ivRight = (AppCompatImageView) findViewById(R.id.iv_right);

        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(R.drawable.icon_home_collect);
        ivRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_right) {
            CC.obtainBuilder(CCName.LIVE)
                    .setActionName(CCName.LIVE_PLATFORM)
                    .build()
                    .callAsync();
        }
    }
}
