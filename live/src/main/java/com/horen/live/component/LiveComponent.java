package com.horen.live.component;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.horen.base.app.CCName;
import com.horen.live.ui.activity.LiveCollectActivity;
import com.horen.live.ui.activity.VideoActivity;
import com.horen.live.ui.fragment.LiveFragment;

/**
 * @author :ChenYangYi
 * @date :2018/07/17/14:04
 * @description :
 * @github :https://github.com/chenyy0708
 * 组件接口
 * 注意：
 * 1. 此接口的实现类代表的是一个组件暴露给外部调用的入口
 * 2. 实现类必须含有一个无参构造方法，以供自动注册插件进行代码注入
 * 3. 实现类有且只有一个对象会被注册到组件库中，故不能为Activity、Fragment等(可以改用动态组件注册{@link LiveComponent})
 */
public class LiveComponent implements IComponent {
    /**
     * 定义组件名称
     *
     * @return 组件的名称
     */
    @Override
    public String getName() {
        return CCName.LIVE;
    }

    /**
     * 组件被调用时的入口
     * 要确保每个逻辑分支都会调用到CC.sendCCResult，
     * 包括try-catch,if-else,switch-case-default,startActivity
     *
     * @param cc 组件调用对象，可从此对象中获取相关信息
     * @return true:将异步调用CC.sendCCResult(...),用于异步实现相关功能，例如：文件加载、网络请求等
     * false:会同步调用CC.sendCCResult(...),即在onCall方法return之前调用，否则将被视为不合法的实现
     */
    @Override
    public boolean onCall(CC cc) {
        String actionName = cc.getActionName();
        switch (actionName) {
            case CCName.LIVE_FRAGMENT: // 直播Fragment
                getLiveFragment(cc);
                break;
            case CCName.LIVE_PLATFORM: // 直播播放
                openLivePlatfromActivity(cc);
                break;
            case CCName.VIDEO_PLAY: // 直播播放
                openVideoPlayActivity(cc);
                break;
            default:
                //这个逻辑分支上没有调用CC.sendCCResult(...),是一种错误的示例
                //并且方法的返回值为false，代表不会异步调用CC.sendCCResult(...)
                //在LocalCCInterceptor中将会返回错误码为-10的CCResult
                break;
        }
        return false;
    }

    private void openVideoPlayActivity(CC cc) {
        Context context = cc.getContext();
        Intent intent = new Intent(context, VideoActivity.class);
        if (!(context instanceof Activity)) {
            //调用方没有设置context或app间组件跳转，context为application
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra("title", cc.getParamItem("title", String.class));
        intent.putExtra("url", cc.getParamItem("url", String.class));
        intent.putExtra("imageUrl", cc.getParamItem("imageUrl", String.class));
        context.startActivity(intent);
        CC.sendCCResult(cc.getCallId(), CCResult.success());
    }

    private void openLivePlatfromActivity(CC cc) {
        Context context = cc.getContext();
        Intent intent = new Intent(context, LiveCollectActivity.class);
        if (!(context instanceof Activity)) {
            //调用方没有设置context或app间组件跳转，context为application
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
        CC.sendCCResult(cc.getCallId(), CCResult.success());
    }

    private void getLiveFragment(CC cc) {
        CC.sendCCResult(cc.getCallId(), CCResult.success(CCName.LIVE_FRAGMENT, LiveFragment.newInstance((Integer) cc.getParamItem("type")))
                .addData("key", CCName.LIVE_FRAGMENT)
        );
    }

}
