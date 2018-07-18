package com.horen.base.app;

import android.content.Context;
import android.content.res.Resources;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.horen.base.net.UrlConstant;
import com.horen.base.util.Utils;

import org.litepal.LitePal;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;


/**
 * APPLICATION
 */
public class BaseApplication extends MultiDexApplication {

    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        Utils.init(this);
        LitePal.initialize(this);
        /**
         * 必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回
         * 第一个参数：应用程序上下文
         * 第二个参数：如果发现滑动返回后立即触摸界面时应用崩溃，请把该界面里比较特殊的 View 的 class 添加到该集合中，目前在库中已经添加了 WebView 和 SurfaceView
         */
        BGASwipeBackHelper.init(this, null);
        RetrofitUrlManager.getInstance().setDebug(true);
        //将每个 BaseUrl 进行初始化,运行时可以随时改变 DOMAIN_NAME 对应的值,从而达到切换 BaseUrl 的效果
        RetrofitUrlManager.getInstance().putDomain(UrlConstant.LIVE, UrlConstant.LIVE_SERVER);
        RetrofitUrlManager.getInstance().putDomain(UrlConstant.MOVIE, UrlConstant.MAO_MI_SERVER);
        RetrofitUrlManager.getInstance().putDomain(UrlConstant.SMALL_VIDEO, UrlConstant.D8_VIDEO_SERVER);
        RetrofitUrlManager.getInstance().putDomain(UrlConstant.SD_LIVE, UrlConstant.SD_SERVER);
    }

    public static Context getAppContext() {
        return baseApplication;
    }

    public static Resources getAppResources() {
        return baseApplication.getResources();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 分包
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
