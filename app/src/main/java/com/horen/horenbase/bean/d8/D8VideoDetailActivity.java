package com.horen.horenbase.bean.d8;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.horen.base.rx.BaseObserver;
import com.horen.base.ui.BaseActivity;
import com.horen.base.util.SPUtils;
import com.horen.horenbase.R;
import com.horen.horenbase.api.Api;
import com.horen.horenbase.api.Constant;
import com.horen.horenbase.api.UrlConstant;
import com.horen.horenbase.rx.RxHelper;
import com.horen.horenbase.utils.GlideUtils;
import com.horen.horenbase.utils.UniCodeUtils;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import butterknife.BindView;

/**
 * @author :ChenYangYi
 * @date :2018/07/03/10:06
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class D8VideoDetailActivity extends BaseActivity {

    @BindView(R.id.post_detail_nested_scroll)
    NestedScrollView postDetailNestedScroll;
    @BindView(R.id.detail_player)
    StandardGSYVideoPlayer detailPlayer;

    private boolean isPlay;
    private boolean isPause;

    private String imageUrl;
    private String title;

    private OrientationUtils orientationUtils;
    private String videoDetailUrl;

    public static void startAction(Context context, String title, String url, String imageUrl) {
        Intent intent = new Intent();
        intent.setClass(context, D8VideoDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.putExtra("imageUrl", imageUrl);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_d8_video_detail;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        if (TextUtils.isEmpty(SPUtils.getSharedStringData(mContext, Constant.FINGER_PRINT))) { // 本地令牌为空，生成一个
            SPUtils.setSharedStringData(mContext, Constant.FINGER_PRINT, "ca9866024f434aed3aeefa903c4c7596");
        }
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        // 获取视频详情
        videoDetailUrl = getIntent().getStringExtra("url");
        imageUrl = getIntent().getStringExtra("imageUrl");
        title = getIntent().getStringExtra("title");
        getVideoInfo(videoDetailUrl);
        getVideoPlayUrl(videoDetailUrl);
    }

    /**
     * 获取视频播放链接
     *
     * @param url
     */
    private void getVideoPlayUrl(String url) {
        mRxManager.add(Api.getService(UrlConstant.D8_VIDEO).d8VideoPlayUrl(url, SPUtils.getSharedStringData(mContext, Constant.FINGER_PRINT))
                .compose(RxHelper.<VideoPlayBean>handleResult())
                .subscribeWith(new BaseObserver<VideoPlayBean>() {
                    @Override
                    protected void _onNext(VideoPlayBean searchDetail) {
                        // 初始化播放器
                        getVideoInfoSuccess(UrlConstant.D8_MEDIA_SERVER + searchDetail.getKeys().getVip(),
                                imageUrl, UniCodeUtils.unicodeToString(title));
                    }

                    @Override
                    protected void _onError(String message) { // token过期，重新生成
                        if (message.equals("播放次数超限，请登录")) {
                            SPUtils.setSharedStringData(mContext, Constant.FINGER_PRINT, "ca9866024f434aed3aeefa903c4c" + UniCodeUtils.getRandomString(4));
                            // 用新的token，重新获取数据
                            getVideoPlayUrl(videoDetailUrl);
                        }
                    }
                }));
    }

    /**
     * 获取视频相关的一些信息
     *
     * @param url
     */
    private void getVideoInfo(String url) {
        mRxManager.add(Api.getService(UrlConstant.D8_VIDEO).d8VideoDetail(url)
                .compose(RxHelper.<VideoDetail>handleResult())
                .subscribeWith(new BaseObserver<VideoDetail>() {
                    @Override
                    protected void _onNext(VideoDetail detail) {

                    }

                    @Override
                    protected void _onError(String message) {

                    }
                }));
    }

    private void getVideoInfoSuccess(String url, String imageUrl, String title) {
        //增加封面
        ImageView imageView = new ImageView(this);
        GlideUtils.load(this, imageUrl, imageView);
        GSYVideoOptionBuilder gsyVideoOption = new GSYVideoOptionBuilder();
        gsyVideoOption.setThumbImageView(imageView)
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setAutoFullWithSize(true)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setUrl(url)
                .setCacheWithPlay(false)
                .setVideoTitle(title)
                .setVideoAllCallBack(new GSYSampleCallBack() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        //开始播放了才能旋转和全屏
                        orientationUtils.setEnable(true);
                        isPlay = true;
                    }

                    @Override
                    public void onEnterFullscreen(String url, Object... objects) {
                        super.onEnterFullscreen(url, objects);
                    }

                    @Override
                    public void onAutoComplete(String url, Object... objects) {
                        super.onAutoComplete(url, objects);
                    }

                    @Override
                    public void onClickStartError(String url, Object... objects) {
                        super.onClickStartError(url, objects);
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        if (orientationUtils != null) {
                            orientationUtils.backToProtVideo();
                        }
                    }
                })
                .setLockClickListener(new LockClickListener() {
                    @Override
                    public void onClick(View view, boolean lock) {
                        if (orientationUtils != null) {
                            //配合下方的onConfigurationChanged
                            orientationUtils.setEnable(!lock);
                        }
                    }
                })
                .build(detailPlayer);

        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detailPlayer.startWindowFullscreen(D8VideoDetailActivity.this, true, true);
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }

        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        getCurPlay().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        getCurPlay().onVideoResume(false);
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (isPlay) {
//            getCurPlay().release();
//        }
        //GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            detailPlayer.onConfigurationChanged(this, newConfig, orientationUtils, true, true);
        }
    }

    private GSYVideoPlayer getCurPlay() {
        if (detailPlayer.getFullWindowPlayer() != null) {
            return detailPlayer.getFullWindowPlayer();
        }
        return detailPlayer;
    }

}
