package com.horen.live.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.horen.live.R;
import com.horen.live.widget.EmptyControlVideo;
import com.jaeger.library.StatusBarUtil;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * @author :ChenYangYi
 * @date :2018/06/27/15:37
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class LivePlayActivity extends AppCompatActivity {

    private StandardGSYVideoPlayer detailPlayer;
    private String title;
    private String url;
    private String imageUrl;

    EmptyControlVideo videoPlayer;

    public static void startAction(Context context, String url, String title, String imageUrl) {
        Intent intent = new Intent();
        intent.setClass(context, LivePlayActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.putExtra("imageUrl", imageUrl);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_activity_live_play);
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        imageUrl = getIntent().getStringExtra("imageUrl");
        StatusBarUtil.setColor(this, Color.BLACK);
        videoPlayer = findViewById(R.id.detail_player);

        videoPlayer.setUp(url, true, "");
        videoPlayer.startPlayLogic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoPlayer.release();
    }

}
