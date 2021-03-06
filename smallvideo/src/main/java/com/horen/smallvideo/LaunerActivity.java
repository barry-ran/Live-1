package com.horen.smallvideo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.horen.smallvideo.ui.fragment.D8Fragment;

import me.yokeyword.fragmentation.SupportActivity;


/**
 * @author :ChenYangYi
 * @date :2018/07/17/17:05
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class LaunerActivity extends SupportActivity {
    private FrameLayout flContiner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smallvideo_activity_launcher);
        flContiner = findViewById(R.id.fl_continer);
        loadRootFragment(R.id.fl_continer, D8Fragment.newInstance());
    }
}
