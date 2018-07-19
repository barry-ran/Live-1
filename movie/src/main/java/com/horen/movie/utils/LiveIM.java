package com.horen.movie.utils;

import android.text.TextUtils;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMValueCallBack;


public class LiveIM {
    private String mGroupId = "";
    private boolean mIsJoinGroupSuccess = false;

    protected final String getGroupId() {
        return this.mGroupId;
    }

    protected final boolean isJoinGroupSuccess() {
        return this.mIsJoinGroupSuccess;
    }

    protected final void setJoinGroupSuccess(boolean joinGroupSuccess) {
        this.mIsJoinGroupSuccess = joinGroupSuccess;
    }

    public final void joinGroup(final String groupId, final TIMCallBack listener) {
        if (TextUtils.isEmpty(groupId)) {
            if (listener != null) {
                listener.onError(-1, "groupId is empty");
            }
        } else if (groupId.equals(getGroupId())) {
            if (listener != null) {
                listener.onSuccess();
            }
        } else if (isJoinGroupSuccess()) {
            if (listener != null) {
                listener.onSuccess();
            }
        } else {
            IMHelper.applyJoinGroup(groupId, "apply join", new TIMCallBack() {
                public void onSuccess() {
                    LiveIM.this.onSuccessJoinGroup(groupId);
                    if (listener != null) {
                        listener.onSuccess();
                    }
                }

                public void onError(int code, String desc) {
                    LiveIM.this.onErrorJoinGroup(groupId, code, desc);
                    if (listener != null) {
                        listener.onError(code, desc);
                    }
                }
            });
        }
    }

    public void onSuccessJoinGroup(String groupId) {
        this.mGroupId = groupId;
        IMHelper.getConversationGroup(groupId).disableStorage();
        setJoinGroupSuccess(true);
    }

    public void onErrorJoinGroup(String groupId, int code, String desc) {
        setJoinGroupSuccess(false);
    }

    public final void quitGroup(final TIMCallBack listener) {
        if (isJoinGroupSuccess()) {
            IMHelper.quitGroup(getGroupId(), new TIMCallBack() {
                public void onSuccess() {
                    if (listener != null) {
                        listener.onSuccess();
                    }
                }

                public void onError(int code, String desc) {
                    if (listener != null) {
                        listener.onError(code, desc);
                    }
                }
            });
            onQuitGroupReset();
            return;
        }
        if (listener != null) {
            listener.onSuccess();
        }
    }

    protected void onQuitGroupReset() {
        setJoinGroupSuccess(false);
        this.mGroupId = "";
    }
}
