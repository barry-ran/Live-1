package com.horen.movie.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.horen.base.app.BaseApplication;
import com.horen.base.net.Constant;
import com.horen.base.util.GsonUtil;
import com.horen.base.util.SPUtils;
import com.horen.domain.sd.SDInitModel;

/**
 * @author :ChenYangYi
 * @date :2018/07/19/09:35
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class InitUtils {
    /**
     * 保存用户信息到SP
     *
     * @param initModel
     */
    public static void saveInfo(SDInitModel initModel) {
        SPUtils.setSharedStringData(BaseApplication.getAppContext(), Constant.APP_INFO, GsonUtil.getGson().toJson(initModel));

    }

    /**
     * 清除用户信息，退出登陆使用
     */
    public static void clearInfo() {
        SPUtils.setSharedStringData(BaseApplication.getAppContext(), Constant.APP_INFO, "");
    }

    private static SDInitModel loginBean = null;

    /**
     * 获取登陆信息
     *
     * @return 登陆信息
     */
    public static SDInitModel getInfo() {
        if (loginBean == null) {
            synchronized (Gson.class) {
                if (loginBean == null) {
                    if (!TextUtils.isEmpty(SPUtils.getSharedStringData(BaseApplication.getAppContext(), Constant.APP_INFO))) { // 用户登陆信息不为空
                        loginBean = GsonUtil.getGson().fromJson(SPUtils.getSharedStringData(BaseApplication.getAppContext(), Constant.APP_INFO), SDInitModel.class);
                    } else { // 用户退出登陆或未登录
                        loginBean = new SDInitModel();
                    }
                }
            }
        }
        return loginBean;
    }
}
