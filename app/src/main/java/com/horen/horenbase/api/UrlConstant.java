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
    public static final int LANGYA = 1;
    public static final String LANGYA_SERVER = "http://128t.cn/so.php";

    /**
     * 直播
     */
    public static final int LIVE = 2;
    public static final String LIVE_SERVER = "http://api.hclyz.cn:81/";

    /**
     * 猫咪直播
     */
    public static final int MAOMI = 3;
    public static final String MAOMI_SERVIER = "http://123.207.59.25:8099/";


    public static String getHost(int type) {
        String host;
        switch (type) {
            case LIVE:
                host = LIVE_SERVER;
                break;
            case MAOMI:
                host = MAOMI_SERVIER;
                break;
            case LANGYA:
                host = LANGYA_SERVER;
                break;
            default:
                host = LIVE_SERVER;
                break;
        }
        return host;
    }
}
