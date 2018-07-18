package com.horen.movie.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author :ChenYangYi
 * @date :2018/07/18/15:40
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class SDParmrsUtils {

    private static Map<String, Object> mData;

    public static String getLiveUrl(String room_id) {
        mData = new LinkedHashMap();
        mData.put("room_id", room_id);
        mData.put("is_vod", "0");
        mData.put("act", "get_video2");
        mData.put("ctl", "video");
        mData.put("screen_height", "1920");
        mData.put("screen_width", "1080");
        mData.put("sdk_type", "android");
        mData.put("sdk_version", "2017111301");
        mData.put("sdk_version_name", "2.5.0");
        return AESUtil.encrypt(SDJsonUtil.object2Json(mData));
    }
}
