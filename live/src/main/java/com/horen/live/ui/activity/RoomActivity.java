package com.horen.live.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.horen.base.ui.BaseActivity;
import com.horen.base.util.SnackbarUtils;
import com.horen.base.util.UniCodeUtils;
import com.horen.domain.live.LiveAnchor;
import com.horen.domain.live.LiveDetail;
import com.horen.live.R;
import com.horen.live.adapter.NewLivePlayAdapter;
import com.horen.live.widget.EmptyControlVideo;
import com.horen.live.widget.VerticalViewPager;
import com.jaeger.library.StatusBarUtil;

import org.litepal.LitePal;

import java.util.ArrayList;

public class RoomActivity extends BaseActivity implements View.OnClickListener {

    private VerticalViewPager mViewPager;
    private RelativeLayout mRoomContainer;
    private FrameLayout mFragmentContainer;
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

    private Toolbar toolBar;
    private TextView tvTitle;
    private AppCompatImageView ivRight;
    private LiveAnchor anchor;

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
        StatusBarUtil.setColor(this, Color.BLACK);
        toolBar = findViewById(R.id.tool_bar);
        tvTitle = findViewById(R.id.tv_title);
        ivRight = findViewById(R.id.iv_right);
        ivRight.setOnClickListener(this);

        mData = (ArrayList<LiveDetail.ZhuboBean>) getIntent().getSerializableExtra("mData");
        liveAnchors = (ArrayList<LiveAnchor>) getIntent().getSerializableExtra("liveAnchors");

        mCurrentItem = getIntent().getIntExtra("position", 0);

        mViewPager = findViewById(R.id.ultra_viewpager);

        mRoomContainer = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.live_view_room_container, null);
        mFragmentContainer = (FrameLayout) mRoomContainer.findViewById(R.id.fragment_container);
        mVideoView = (EmptyControlVideo) mRoomContainer.findViewById(R.id.detail_player);

        mFragmentManager = getSupportFragmentManager();

        mPagerAdapter = new NewLivePlayAdapter(mContext, mData, liveAnchors);


        initToolbar(toolBar, false);
        // 查询数据库
        anchor = LitePal.where("url=?", mData != null ? mData.get(mCurrentItem).getAddress() : liveAnchors.get(mCurrentItem).getUrl())
                .findFirst(LiveAnchor.class);
        tvTitle.setText(UniCodeUtils.unicodeToString(mData != null ? mData.get(mCurrentItem).getTitle() : liveAnchors.get(mCurrentItem).getName()));
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
                tvTitle.setText(UniCodeUtils.unicodeToString(mData != null ? mData.get(position).getTitle() : liveAnchors.get(position).getName()));
                checkCollectState(anchor);
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
        mVideoView.setUp(mData != null ? mData.get(position).getAddress() : liveAnchors.get(position).getImageUrl(), false, null);
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
            mVideoView.onVideoResume();
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
}
