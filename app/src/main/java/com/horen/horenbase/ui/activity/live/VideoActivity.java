package com.horen.horenbase.ui.activity.live;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.horen.horenbase.R;
import com.horen.horenbase.bean.LiveAnchor;
import com.horen.horenbase.utils.SnackbarUtils;
import com.jaeger.library.StatusBarUtil;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.litepal.LitePal;

/**
 * @author :ChenYangYi
 * @date :2018/06/27/15:37
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class VideoActivity extends GSYBaseActivityDetail<StandardGSYVideoPlayer> {

    private StandardGSYVideoPlayer detailPlayer;
    private String title;
    private String url;
    private String imageUrl;
    private LiveAnchor anchor;

    public static void startAction(Context context, String url, String title, String imageUrl) {
        Intent intent = new Intent();
        intent.setClass(context, VideoActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.putExtra("imageUrl", imageUrl);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        imageUrl = getIntent().getStringExtra("imageUrl");
        StatusBarUtil.setColor(this, Color.BLACK);
        detailPlayer = (StandardGSYVideoPlayer) findViewById(R.id.detail_player);
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        detailPlayer.getBackButton().setVisibility(View.VISIBLE);
        //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        detailPlayer.setAutoFullWithSize(true);
        initVideoBuilderMode();
        detailPlayer.startPlayLogic();
        if (TextUtils.isEmpty(imageUrl)) { // 非主播链接播放
            detailPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        } else { // 主播链接
            anchor = LitePal.where("url=?", url)
                    .findFirst(LiveAnchor.class);
            checkCollectState(anchor);
            detailPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (anchor == null) { // 没有收藏过
                        anchor = new LiveAnchor.Builder()
                                .setUrl(url)
                                .setImageUrl(imageUrl)
                                .setName(title)
                                .builder();
                        if (anchor.save())
                            SnackbarUtils.show(VideoActivity.this, getString(R.string.collect_success));
                    } else { // 已经收藏，删除收藏
                        anchor.delete();
                        anchor = null;
                        SnackbarUtils.show(VideoActivity.this, getString(R.string.collect_cancle));
                    }
                    checkCollectState(anchor);
                }
            });
        }

    }

    @Override
    public StandardGSYVideoPlayer getGSYVideoPlayer() {
        return detailPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        return new GSYVideoOptionBuilder()
                .setUrl(getIntent().getStringExtra("url"))
                .setVideoTitle(getIntent().getStringExtra("title"))
                .setIsTouchWiget(true)
                .setStartAfterPrepared(true);
    }

    @Override
    public void clickForFullScreen() {

    }

    @Override
    public boolean getDetailOrientationRotateAuto() {
        return false;
    }

    private void checkCollectState(LiveAnchor anchor) {
        if (anchor == null) {
            detailPlayer.getBackButton().setImageResource(R.drawable.icon_un_collect);
        } else {
            detailPlayer.getBackButton().setImageResource(R.drawable.icon_collect);
        }
    }
}
