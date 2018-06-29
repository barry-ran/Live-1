package com.horen.horenbase.ui.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.horen.base.ui.BaseActivity;
import com.horen.horenbase.R;
import com.horen.horenbase.ui.activity.live.LiveFragment;
import com.horen.horenbase.utils.SnackbarUtils;

import butterknife.BindView;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author :ChenYangYi
 * @date :2018/06/29/08:47
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class MainActivity extends BaseActivity implements ISupportActivity, BottomNavigationView.OnNavigationItemSelectedListener {

    /**
     * 不需要继承SupportActivity，用于处理Fragment内存回收各种情况
     */
    final SupportActivityDelegate mDelegate = new SupportActivityDelegate(this);
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    private SupportFragment[] mFragments = new SupportFragment[4];
    public static final int FIRST = 0;
    public static final int SECOND = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDelegate.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mFragments[FIRST] = LiveFragment.newInstance();
            mFragments[SECOND] = LiveFragment.newInstance();
            loadMultipleRootFragment(R.id.fl_container, FIRST, mFragments[FIRST],
                    mFragments[SECOND]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            mFragments[FIRST] = findFragment(LiveFragment.class);
            mFragments[SECOND] = findFragment(LiveFragment.class);
        }
        // 设置选中
        navigation.setSelectedItemId(R.id.navigation_live);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        navigation.setOnNavigationItemSelectedListener(this);
        initToolbar(toolBar, false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        tvTitle.setText(R.string.live);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDelegate.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mDelegate.onDestroy();
        super.onDestroy();
    }


    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return mDelegate;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return mDelegate.extraTransaction();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return mDelegate.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        mDelegate.setFragmentAnimator(fragmentAnimator);
    }

    /**
     * Set all fragments animation.
     * 构建Fragment转场动画
     * <p/>
     * 如果是在Activity内实现,则构建的是Activity内所有Fragment的转场动画,
     * 如果是在Fragment内实现,则构建的是该Fragment的转场动画,此时优先级 > Activity的onCreateFragmentAnimator()
     *
     * @return FragmentAnimator对象
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return mDelegate.onCreateFragmentAnimator();
    }

    @Override
    public void post(Runnable runnable) {
        mDelegate.post(runnable);
    }

    @Override
    public void onBackPressedSupport() {
        mDelegate.onBackPressedSupport();
    }


    private long clickTime;

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        long time = 2000;
        if ((currentTime - clickTime) > time) {
            SnackbarUtils.showSnackMessage(this, "再按一次后退键退出");
            clickTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    /**
     * 加载多个同级根Fragment,类似WeChat, QQ主页的场景
     */
    public void loadMultipleRootFragment(int containerId, int showPosition, ISupportFragment... toFragments) {
        mDelegate.loadMultipleRootFragment(containerId, showPosition, toFragments);
    }

    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getSupportFragmentManager(), fragmentClass);
    }

    /**
     * show一个Fragment,hide其他同栈所有Fragment
     * 使用该方法时，要确保同级栈内无多余的Fragment,(只有通过loadMultipleRootFragment()载入的Fragment)
     * <p>
     * 建议使用更明确的{@link # showHideFragment(ISupportFragment, ISupportFragment)}
     *
     * @param showFragment 需要show的Fragment
     */
    public void showHideFragment(ISupportFragment showFragment) {
        mDelegate.showHideFragment(showFragment);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // 直播
            case R.id.navigation_live:
                tvTitle.setText(R.string.live);
                showHideFragment(mFragments[FIRST]);
                return true;
            // 其他
            case R.id.navigation_other:
                tvTitle.setText(R.string.other);
                showHideFragment(mFragments[SECOND]);
                return true;
            default:
                break;
        }
        return false;
    }
}