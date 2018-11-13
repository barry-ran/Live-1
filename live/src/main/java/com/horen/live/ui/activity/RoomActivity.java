package com.horen.live.ui.activity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.horen.base.ui.BaseActivity;
import com.horen.base.util.GlideUtils;
import com.horen.base.util.SnackbarUtils;
import com.horen.base.util.UniCodeUtils;
import com.horen.domain.live.LiveAnchor;
import com.horen.domain.live.LiveDetail;
import com.horen.live.R;
import com.horen.live.adapter.NewLivePlayAdapter;
import com.horen.live.widget.EmptyControlVideo;
import com.horen.live.widget.VerticalViewPager;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;

import org.litepal.LitePal;

import java.util.ArrayList;

public class RoomActivity extends BaseActivity implements View.OnClickListener {

    private VerticalViewPager mViewPager;
    private RelativeLayout mRoomContainer;
    private EmptyControlVideo mVideoView;

    private int mCurrentItem;
    private PagerAdapter mPagerAdapter;
    private int isLiveStreaming = 1;
    private FragmentManager mFragmentManager;
    private int mRoomId = -1;

    private boolean mInit = false;

    private boolean mIsActivityPaused = true;

    private String mVideoPath = null;

    private static final int MESSAGE_ID_RECONNECTING = 0x01;

    private ArrayList<LiveDetail.ZhuboBean> mData;
    private ArrayList<LiveAnchor> liveAnchors;

    private boolean isHide = false;

    private Toolbar toolBar;
    private AppCompatImageView ivRight;
    private LiveAnchor anchor;
    private ImageView ivVideoNormal;
    private View view;

    public static void startAction(Context context, ArrayList<LiveDetail.ZhuboBean> mData, ArrayList<LiveAnchor> liveAnchors, int position) {
        Intent intent = new Intent();
        intent.setClass(context, RoomActivity.class);
        intent.putExtra("mData", mData);
        intent.putExtra("liveAnchors", liveAnchors);
        intent.putExtra("position", position);
        context.startActivity(intent);

    }

    @Override
    public int getLayoutId() {
        return R.layout.live_activity_live_play;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);


        toolBar = findViewById(R.id.tool_bar);
        ivRight = findViewById(R.id.iv_right);
        ivRight.setOnClickListener(this);

        mData = (ArrayList<LiveDetail.ZhuboBean>) getIntent().getSerializableExtra("mData");
        liveAnchors = (ArrayList<LiveAnchor>) getIntent().getSerializableExtra("liveAnchors");

        mCurrentItem = getIntent().getIntExtra("position", 0);

        mViewPager = findViewById(R.id.ultra_viewpager);
        mRoomContainer = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.live_view_room_container, null);
        mVideoView = (EmptyControlVideo) mRoomContainer.findViewById(R.id.detail_player);
        ivVideoNormal = (ImageView) mRoomContainer.findViewById(R.id.iv_video_normal);
        mFragmentManager = getSupportFragmentManager();

        mPagerAdapter = new NewLivePlayAdapter(mContext, mData, liveAnchors);
        // 点击屏幕隐藏标题栏
        view = mRoomContainer.findViewById(R.id.view_click);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHide) {
                    translationView(toolBar, -toolBar.getHeight(), 0, 200);
                } else {
                    translationView(toolBar, 0, -toolBar.getHeight(), 200);
                }
                isHide = !isHide;
            }
        });
        // 延时几秒隐藏Toolbar
        toolBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                translationView(toolBar, 0, -toolBar.getHeight(), 200);
                isHide = true;
            }
        }, 3000);
        initToolbar(toolBar, false);
        // 查询数据库
        anchor = LitePal.where("url=?", mData != null ? mData.get(mCurrentItem).getAddress() : liveAnchors.get(mCurrentItem).getUrl())
                .findFirst(LiveAnchor.class);
        toolBar.setTitle(UniCodeUtils.unicodeToString(mData != null ? mData.get(mCurrentItem).getTitle() : liveAnchors.get(mCurrentItem).getName()));
        checkCollectState(anchor);


        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mCurrentItem = position;
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // 查询数据库
                anchor = LitePal.where("url=?", mData != null ? mData.get(position).getAddress() : liveAnchors.get(position).getUrl())
                        .findFirst(LiveAnchor.class);
                toolBar.setTitle(UniCodeUtils.unicodeToString(mData != null ? mData.get(position).getTitle() : liveAnchors.get(position).getName()));
                checkCollectState(anchor);

                GlideUtils.load(mContext, mData != null ? mData.get(position).getImg() : liveAnchors.get(position).getImageUrl(), ivVideoNormal);
            }
        });

        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                page.setTranslationX(page.getWidth() * -position);
                float yPosition = position * page.getHeight();
                page.setTranslationY(yPosition);

                ViewGroup viewGroup = (ViewGroup) page;
                if ((position < 0 && viewGroup.getId() != mCurrentItem)) {
                    View roomContainer = viewGroup.findViewById(R.id.room_container);
                    if (roomContainer != null && roomContainer.getParent() != null && roomContainer.getParent() instanceof ViewGroup) {
                        ((ViewGroup) (roomContainer.getParent())).removeView(roomContainer);
                    }
                }
                // 满足此种条件，表明需要加载直播视频，以及聊天室了
                if (viewGroup.getId() == mCurrentItem && position == 0 && mCurrentItem != mRoomId) {
                    if (mRoomContainer.getParent() != null && mRoomContainer.getParent() instanceof ViewGroup) {
                        ((ViewGroup) (mRoomContainer.getParent())).removeView(mRoomContainer);
                    }
                    loadVideoAndChatRoom(viewGroup, mCurrentItem);
                }
            }
        });
        mViewPager.setAdapter(mPagerAdapter);

        mViewPager.setCurrentItem(mCurrentItem);

        GlideUtils.load(mContext, mData != null ? mData.get(mCurrentItem).getImg() : liveAnchors.get(mCurrentItem).getImageUrl(), ivVideoNormal);


        mVideoView.setVideoAllCallBack(new GSYSampleCallBack() {

            @Override
            public void onStartPrepared(String url, Object... objects) {
                ivVideoNormal.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPlayError(String url, Object... objects) {
            }

            @Override
            public void onPrepared(String url, Object... objects) {
                ivVideoNormal.setVisibility(View.GONE);
            }
        });
    }

    /**
     * @param viewGroup
     * @param currentItem
     */
    private void loadVideoAndChatRoom(ViewGroup viewGroup, int currentItem) {
        loadVideo(currentItem);
        viewGroup.addView(mRoomContainer);
        mRoomId = currentItem;
    }

    private void loadVideo(int position) {
        mVideoView.setUp(mData != null ? mData.get(position).getAddress() : liveAnchors.get(position).getUrl(), false, null);
        mVideoView.startPlayLogic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null)
            mVideoView.release();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView != null)
            mVideoView.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mVideoView != null)
            mVideoView.onVideoResume(false);
    }

    private void checkCollectState(LiveAnchor anchor) {
        if (anchor == null) {
            ivRight.setImageResource(R.drawable.icon_un_collect);
        } else {
            ivRight.setImageResource(R.drawable.icon_collect);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_right) {
            if (anchor == null) { // 没有收藏过
                anchor = new LiveAnchor.Builder()
                        .setUrl(mData != null ? mData.get(mCurrentItem).getAddress() : liveAnchors.get(mCurrentItem).getUrl())
                        .setImageUrl(mData != null ? mData.get(mCurrentItem).getImg() : liveAnchors.get(mCurrentItem).getImageUrl())
                        .setName(UniCodeUtils.unicodeToString(mData != null ? mData.get(mCurrentItem).getTitle() : liveAnchors.get(mCurrentItem).getName()))
                        .builder();
                if (anchor.save())
                    SnackbarUtils.show(RoomActivity.this, getString(R.string.collect_success));
            } else { // 已经收藏，删除收藏
                anchor.delete();
                anchor = null;
                SnackbarUtils.show(RoomActivity.this, getString(R.string.collect_cancle));
            }
            checkCollectState(anchor);
        }
    }

    /**
     * 平移动画
     *
     * @param view     view
     * @param fromY    开始位置
     * @param toY      结束位置
     * @param duration 动画时间
     */
    public void translationView(View view, float fromY, float toY, int duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", fromY, toY);
        animator.setDuration(duration);
        animator.start();
    }
}
