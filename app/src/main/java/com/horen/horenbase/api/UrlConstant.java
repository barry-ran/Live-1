package com.horen.horenbase.api;

/**
 * @author :ChenYangYi
 * @date :2018/07/02/13:49
 * @description :多URL管理
 * @github :https://github.com/chenyy0708
 */
public class UrlConstant {
    /**
     * 狼牙
     */
    public static final int LANG_YA = 1;
    public static final String LANG_YA_SERVER = "http://128t.cn/so.php/";
    /**
     * 狼牙
     */
    public static final int LANG_YA_DETAIL = 4;
    public static final String LANG_YA_DETAIL_SERVER = "http://128t.cn/xvideos.php/";

    /**
     * 直播
     */
    public static final int LIVE = 2;
    public static final String LIVE_SERVER = "http://api.hclyz.cn:81/";

    /**
     * 猫咪直播
     */
    public static final int MAO_MI = 3;
    public static final String MAO_MI_SERVER = "http://123.207.59.25:8099/";


    public static String getHost(int type) {
        String host;
        switch (type) {
            case LIVE:
                host = LIVE_SERVER;
                break;
            case MAO_MI:
                host = MAO_MI_SERVER;
                break;
            case LANG_YA:
                host = LANG_YA_SERVER;
                break;
                case LANG_YA_DETAIL:
                host = LANG_YA_DETAIL_SERVER;
                break;
            default:
                host = LIVE_SERVER;
                break;
        }
        return host;
    }
}
