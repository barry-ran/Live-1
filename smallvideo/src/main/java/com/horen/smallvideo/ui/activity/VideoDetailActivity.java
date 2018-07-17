package com.horen.smallvideo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.billy.cc.core.component.CC;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.horen.base.app.CCName;
import com.horen.base.net.Api;
import com.horen.base.net.Constant;
import com.horen.base.net.UrlConstant;
import com.horen.base.rx.BaseObserver;
import com.horen.base.rx.RxHelper;
import com.horen.base.ui.BaseActivity;
import com.horen.base.util.GlideUtils;
import com.horen.base.util.SPUtils;
import com.horen.base.util.UniCodeUtils;
import com.horen.domain.d8.VideoBean;
import com.horen.domain.d8.VideoDetail;
import com.horen.domain.d8.VideoPlayBean;
import com.horen.smallvideo.R;
import com.horen.smallvideo.R2;
import com.horen.smallvideo.adapter.SearchVideoAdapter;
import com.horen.smallvideo.adapter.VideoTagAdapter;
import com.jaeger.library.StatusBarUtil;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author :ChenYangYi
 * @date :2018/07/03/10:06
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class VideoDetailActivity extends BaseActivity {

    @BindView(R2.id.post_detail_nested_scroll)
    NestedScrollView postDetailNestedScroll;
    @BindView(R2.id.detail_player)
    StandardGSYVideoPlayer detailPlayer;
    @BindView(R2.id.tv_watch_count)
    TextView tvWatchCount;
    @BindView(R2.id.tv_release_time)
    TextView tvReleaseTime;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.rv_tag)
    RecyclerView rvTag;
    @BindView(R2.id.rv_recommend_video)
    RecyclerView rvRecommendVideo;

    private boolean isPlay;
    private boolean isPause;

    private String imageUrl;
    private String title;

    private OrientationUtils orientationUtils;
    private String videoDetailUrl;
    private SearchVideoAdapter recommentAdapter;
    private VideoTagAdapter tagAdapter;

    public static void startAction(Context context, String title, String url, String imageUrl) {
        Intent intent = new Intent();
        intent.setClass(context, VideoDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.putExtra("imageUrl", imageUrl);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.smallvideo_activity_video_detail;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(mContext,R.color.black));
        rvTag.setNestedScrollingEnabled(false);
        rvRecommendVideo.setNestedScrollingEnabled(false);
        rvTag.setLayoutManager(new FlexboxLayoutManager(mContext));
        rvRecommendVideo.setLayoutManager(new GridLayoutManager(mContext, 2));
        tagAdapter = new VideoTagAdapter(R.layout.smallvideo_item_tag, new ArrayList<VideoDetail.VideoBean.TagsBean>());
        recommentAdapter = new SearchVideoAdapter(R.layout.smallvideo_item_search, new ArrayList<VideoBean>());
        rvTag.setAdapter(tagAdapter);
        rvRecommendVideo.setAdapter(recommentAdapter);
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
        recommentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoBean bean = recommentAdapter.getData().get(position);
                CC.obtainBuilder(CCName.SMALL_VIDEO)
                        .setActionName(CCName.SMALL_VIDEO_DETAIL)
                        .addParam("title", bean.getTitle())
                        .addParam("url", bean.getId_encrypt())
                        .addParam("imageUrl", UniCodeUtils.replaceHttpUrl(bean.getThumb_href()))
                        .build()
                        .callAsync();
                finish();
            }
        });
    }

    /**
     * 获取视频播放链接
     *
     * @param url
     */
    private void getVideoPlayUrl(String url) {
        mRxManager.add(Api.getService(UrlConstant.D8_VIDEO).d8VideoPlayUrl(url, SPUtils.getSharedStringData(mContext, Constant.FINGER_PRINT))
                .compose(RxHelper.<VideoPlayBean>handleResult())
                .subscribeWith(new BaseObserver<VideoPlayBean>(mContext, true) {
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
                        initRecommendVideo(detail);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                }));
    }

    /**
     * 初始化推荐视频信息
     */
    private void initRecommendVideo(VideoDetail detail) {
        tvWatchCount.setText(String.valueOf(detail.getVideo().getPlay_count()));
        tvReleaseTime.setText(detail.getVideo().getReleased_at().getDate());
        tvTitle.setText(UniCodeUtils.unicodeToString(detail.getVideo().getTitle()));
        tagAdapter.setNewData(detail.getVideo().getTags());
        recommentAdapter.setNewData(detail.getGuess_like());
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
        detailPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detailPlayer.startWindowFullscreen(VideoDetailActivity.this, true, true);
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
        if (isPlay) {
            getCurPlay().release();
        }
        //GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
        super.onDestroy();
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
