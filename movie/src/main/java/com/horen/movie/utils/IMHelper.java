package com.horen.movie.utils;

import android.text.TextUtils;
import android.util.Log;

import com.horen.domain.sd.SDInitModel;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.ext.message.TIMConversationExt;

public class IMHelper {
    private static final String TAG = "IMHelper";
    private static boolean isInLogin = false;
    private static TIMCallBack loginCall = new TIMCallBack() {
        @Override
        public void onError(int i, String s) {
            isInLogin = false;
            Log.d(TAG, "login IM error");
        }

        @Override
        public void onSuccess() {
            isInLogin = false;
            Log.d(TAG, "login IM success");
//            joinAppGroup();
//            joinOnlineGroup();
        }
    };

    private static TIMCallBack groupCall = new TIMCallBack() {
        @Override
        public void onError(int i, String s) {

        }

        @Override
        public void onSuccess() {

        }
    };

    /**
     * 登陆
     *
     * @param user_id
     * @param user_sig
     */
    public static void login(String user_id, String user_sig) {
        if (!isInLogin) {
            String loginUser = TIMManager.getInstance().getLoginUser();
            if (TextUtils.isEmpty(loginUser)) {
                isInLogin = true;
                TIMManager.getInstance().login(user_id, user_sig, loginCall);
            }
        }
    }

    private static void joinAppGroup() {
        SDInitModel initModel = InitUtils.getInfo();
        String groupId = initModel.getFull_group_id();
        if (!TextUtils.isEmpty(groupId)) {
            applyJoinGroup(groupId, "apply join android", groupCall);
        }
    }

    private static void joinOnlineGroup() {
        SDInitModel initModel = InitUtils.getInfo();
        String groupId = initModel.getOn_line_group_id();
        if (!TextUtils.isEmpty(groupId)) {
            applyJoinGroup(groupId, "apply join android", null);
        }
    }

    /**
     * 加入群组
     */
    public static void applyJoinGroup(final String groupId, String reason, final TIMCallBack callback) {
        if (!TextUtils.isEmpty(groupId)) {
            TIMGroupManager.getInstance().applyJoinGroup(groupId, reason, new TIMCallBack() {
                @Override
                public void onSuccess() {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("applyJoinGroup success:");
                    stringBuilder.append(groupId);
                    Log.d(TAG, stringBuilder.toString());
                    if (callback != null) {
                        callback.onSuccess();
                    }
                }

                @Override
                public void onError(int code, String desc) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("applyJoinGroup error:");
                    stringBuilder.append(groupId);
                    stringBuilder.append(",");
                    stringBuilder.append(code);
                    stringBuilder.append(",");
                    stringBuilder.append(desc);
                    Log.d(TAG, stringBuilder.toString());
                    if (callback != null) {
                        callback.onError(code, desc);
                    }
                }
            });
        }
    }

    public static void quitOnlineGroup(TIMCallBack callback) {
        SDInitModel actModel = InitUtils.getInfo();
        if (actModel == null) {
            if (callback != null) {
                callback.onSuccess();
            }
            return;
        }
        String groupId = actModel.getOn_line_group_id();
        if (TextUtils.isEmpty(groupId)) {
            if (callback != null) {
                callback.onSuccess();
            }
            return;
        }
        quitGroup(groupId, callback);
    }

    public static void quitGroup(final String groupId, final TIMCallBack callback) {
        if (!TextUtils.isEmpty(groupId)) {
            TIMGroupManager.getInstance().quitGroup(groupId, new TIMCallBack() {
                public void onSuccess() {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("quitGroup success:");
                    stringBuilder.append(groupId);
                    Log.d(TAG, stringBuilder.toString());
                    if (callback != null) {
                        callback.onSuccess();
                    }
                }

                public void onError(int code, String desc) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("quitGroup error:");
                    stringBuilder.append(groupId);
                    stringBuilder.append(",");
                    stringBuilder.append(code);
                    stringBuilder.append(",");
                    stringBuilder.append(desc);
                    Log.d(TAG, stringBuilder.toString());
                    if (callback != null) {
                        callback.onError(code, desc);
                    }
                }
            });
        }
    }

    public static TIMConversationExt getConversationGroup(String id) {
        if (TextUtils.isEmpty(id)) {
            return null;
        }
        TIMConversation conversation = TIMManager.getInstance().getConversation(TIMConversationType.Group, id);
        return new TIMConversationExt(conversation);
    }
}
