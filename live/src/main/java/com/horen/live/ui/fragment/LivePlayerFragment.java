package com.horen.live.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.horen.base.ui.BaseFragment;
import com.horen.live.R;
import com.horen.live.widget.EmptyControlVideo;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;

/**
 * @author :ChenYangYi
 * @date :2018/08/16/15:05
 * @description :播放直播Fragment
 * @github :https://github.com/chenyy0708
 */
public class LivePlayerFragment extends BaseFragment {

    EmptyControlVideo videoPlayer;
    private String url;
    private ImageView imageView;

    public static LivePlayerFragment newInstance(String url) {
        Bundle args = new Bundle();
        LivePlayerFragment fragment = new LivePlayerFragment();
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.live_fragment_live_play;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void initView() {
        videoPlayer = rootView.findViewById(R.id.detail_player);
        imageView = rootView.findViewById(R.id.iv);
        url = getArguments().getString("url");
        videoPlayer.setUp(url, true, "");
        videoPlayer.startPlayLogic();

        videoPlayer.setVideoAllCallBack(
                new GSYSampleCallBack() {
                    // 加载成功
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        imageView.setVisibility(View.GONE);
                    }
                }
        );
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (videoPlayer != null)
            videoPlayer.release();
    }
}
