package com.horen.horenbase.api;

/**
 * @author :ChenYangYi
 * @date :2018/07/02/13:49
 * @description :多URL管理
 * @github :https://github.com/chenyy0708
 */
public class UrlConstant {
    /**
     * 直播
     */
    public static final int LIVE = 2;
    private static final String LIVE_SERVER = "http://api.hclyz.cn:81/";

    /**
     * 猫咪直播
     */
    public static final int MAO_MI = 3;
    private static final String MAO_MI_SERVER = "http://123.207.59.25:8099/";

    /**
     * D8小视频
     * http://email.d8dizhi.at.gmail.com.do88.space/m
     */
    public static final int D8_VIDEO = 5;
    private static final String D8_VIDEO_SERVER = "http://email.d8dizhi.at.gmail.com.d8-app.space/";
    // D8视频播放服务器地址
    public static final String D8_MEDIA_SERVER = "http://md.vsilent.space/";


    public static String getHost(int type) {
        String host;
        switch (type) {
            case LIVE:
                host = LIVE_SERVER;
                break;
            case MAO_MI:
                host = MAO_MI_SERVER;
                break;
            case D8_VIDEO:
                host = D8_VIDEO_SERVER;
                break;
            default:
                host = LIVE_SERVER;
                break;
        }
        return host;
    }
}
