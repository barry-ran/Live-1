package com.horen.movie.utils;

import android.text.TextUtils;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMManager;

public class IMHelper {
    private static final String TAG = "IMHelper";
    private static boolean isInLogin = false;

    public static void loginIM(String userId, String userSig) {
        if (!isInLogin) {

        }
    }

    public static TIMConversation getConversationC2C(String id) {
        if (TextUtils.isEmpty(id)) {
            return null;
        }
        return TIMManager.getInstance().getConversation(TIMConversationType.C2C, id);
    }

    public static TIMConversation getConversationGroup(String id) {
        if (TextUtils.isEmpty(id)) {
            return null;
        }
        return TIMManager.getInstance().getConversation(TIMConversationType.Group, id);
    }

    public static void applyJoinGroup(final String groupId, String reason, final TIMCallBack callback) {
        if (!TextUtils.isEmpty(groupId)) {
            TIMGroupManager.getInstance().applyJoinGroup(groupId, reason, new TIMCallBack() {
                public void onSuccess() {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("applyJoinGroup success:");
                    stringBuilder.append(groupId);
                    if (callback != null) {
                        callback.onSuccess();
                    }
                }

                public void onError(int code, String desc) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("applyJoinGroup error:");
                    stringBuilder.append(groupId);
                    stringBuilder.append(",");
                    stringBuilder.append(code);
                    stringBuilder.append(",");
                    stringBuilder.append(desc);
                    if (callback != null) {
                        callback.onError(code, desc);
                    }
                }
            });
        }
    }

    public static void quitGroup(final String groupId, final TIMCallBack callback) {
        if (!TextUtils.isEmpty(groupId)) {
            TIMGroupManager.getInstance().quitGroup(groupId, new TIMCallBack() {
                public void onSuccess() {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("quitGroup success:");
                    stringBuilder.append(groupId);
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
                    if (callback != null) {
                        callback.onError(code, desc);
                    }
                }
            });
        }
    }

}
