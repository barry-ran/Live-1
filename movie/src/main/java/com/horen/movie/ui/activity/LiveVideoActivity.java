package com.horen.movie.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.horen.base.util.GsonUtil;
import com.horen.base.util.SnackbarUtils;
import com.horen.domain.live.LiveAnchor;
import com.horen.movie.R;
import com.horen.movie.bean.CustomMsg;
import com.horen.movie.utils.LiveIM;
import com.jaeger.library.StatusBarUtil;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMCustomElem;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMTextElem;

import org.litepal.LitePal;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/06/27/15:37
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class LiveVideoActivity extends GSYBaseActivityDetail<StandardGSYVideoPlayer> implements TIMCallBack, TIMMessageListener {

    private StandardGSYVideoPlayer detailPlayer;
    private String title;
    private String url;
    private String imageUrl;
    private LiveAnchor anchor;
    private String group_id;
    private LiveIM liveIM;

    public static void startAction(Context context, String url, String title, String imageUrl, String group_id) {
        Intent intent = new Intent();
        intent.setClass(context, LiveVideoActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.putExtra("imageUrl", imageUrl);
        intent.putExtra("group_id", group_id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_activity_video);
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        imageUrl = getIntent().getStringExtra("imageUrl");
        StatusBarUtil.setColor(this, Color.BLACK);
        detailPlayer = (StandardGSYVideoPlayer) findViewById(R.id.detail_player);
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        detailPlayer.getBackButton().setVisibility(View.VISIBLE);
        //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        detailPlayer.setAutoFullWithSize(true);
        initVideoBuilderMode();
        detailPlayer.startPlayLogic();
        if (TextUtils.isEmpty(imageUrl)) { // 非主播链接播放
            detailPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        } else { // 主播链接
            anchor = LitePal.where("url=?", url)
                    .findFirst(LiveAnchor.class);
            checkCollectState(anchor);
            detailPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (anchor == null) { // 没有收藏过
                        anchor = new LiveAnchor.Builder()
                                .setUrl(url)
                                .setImageUrl(imageUrl)
                                .setName(title)
                                .builder();
                        if (anchor.save())
                            SnackbarUtils.show(LiveVideoActivity.this, getString(R.string.collect_success));
                    } else { // 已经收藏，删除收藏
                        anchor.delete();
                        anchor = null;
                        SnackbarUtils.show(LiveVideoActivity.this, getString(R.string.collect_cancle));
                    }
                    checkCollectState(anchor);
                }
            });
        }
        group_id = getIntent().getStringExtra("group_id");
        liveIM = new LiveIM();
        // 加入主播群组
        liveIM.joinGroup(group_id, this);
    }

    @Override
    public StandardGSYVideoPlayer getGSYVideoPlayer() {
        return detailPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        return new GSYVideoOptionBuilder()
                .setUrl(getIntent().getStringExtra("url"))
                .setVideoTitle(getIntent().getStringExtra("title"))
                .setIsTouchWiget(true)
                .setStartAfterPrepared(true);
    }

    @Override
    public void clickForFullScreen() {

    }

    @Override
    public boolean getDetailOrientationRotateAuto() {
        return false;
    }

    private void checkCollectState(LiveAnchor anchor) {
        if (anchor == null) {
            detailPlayer.getBackButton().setImageResource(R.drawable.icon_un_collect);
        } else {
            detailPlayer.getBackButton().setImageResource(R.drawable.icon_collect);
        }
    }

    @Override
    public void onError(int i, String s) {
        Log.d("IMHelper", "onError " + i + "---" + s);
    }

    @Override
    public void onSuccess() {
        // 加入群组成功，保存group_id
        liveIM.onSuccessJoinGroup(group_id);
        Log.d("IMHelper", "加入主播群组成功 ");
        TIMManager.getInstance().addMessageListener(this);
    }

    /**
     * 解析群组消息
     */
    private void parseData(List<TIMMessage> list) {
        TIMMessage msg = list.get(0);
        for (int i = 0; i < msg.getElementCount(); ++i) {
            TIMElem elem = msg.getElement(i);
            //获取当前元素的类型
            TIMElemType elemType = elem.getType();
            if (elemType == TIMElemType.Custom) {
                TIMCustomElem timCustomElem = (TIMCustomElem) elem;
                try {
                    CustomMsg customMsg = GsonUtil.getGson().fromJson(new String(timCustomElem.getData(), "UTF-8"), CustomMsg.class);
                    if (customMsg.getType() == 5) { // 进入
                        Log.d("IMHelper", "parseData: " + customMsg.getSender().getNick_name() + "来了");
                    } else if (customMsg.getType() == 6) { // 离开
                        Log.d("IMHelper", "parseData: " + customMsg.getSender().getNick_name() + "离开");
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else if (elemType == TIMElemType.Text) {
                TIMTextElem textElem = (TIMTextElem) elem;
                Log.d("IMHelper", "Text: " + textElem.getText());
            }
        }
    }

    @Override
    protected void onDestroy() {
        liveIM.quitGroup(null);
        TIMManager.getInstance().removeMessageListener(this);
        super.onDestroy();
    }

    /**
     * 接受消息
     */
    @Override
    public boolean onNewMessages(List<TIMMessage> list) {
        parseData(list);
        return false;
    }
}
