package com.horen.base.util;

import com.bumptech.glide.load.Key;
import com.horen.base.net.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.TreeMap;

import tv.danmaku.ijk.media.player.IjkMediaMeta;

/**
 * @author :ChenYangYi
 * @date :2018/06/29/14:34
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class ParmsUtils {

    private static Map<String, String> parms;

    /**
     * 公共的参数
     *
     * @return
     */
    public static Map<String, String> getParms() {
        parms = new TreeMap<>();
        parms.put("_sdk_version", Utils.getSDKVersion());
        parms.put("ms_app_version", Utils.getAppVersionName(Utils.getAppContext()));
        parms.put("ms_device_name", Utils.getModel());
        parms.put("ms_deviceid", "865422032013740");
        parms.put("ms_system_version", Utils.getOSVersion());
        return parms;
    }


    /**
     * 首页视频
     *
     * @param page
     * @param perPage
     * @param currentTylp
     * @return
     */
    public static Map<String, String> getMovieList(int page, int perPage, int currentTylp) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", page);
            jsonObject.put("perPage", perPage);
            jsonObject.put(IjkMediaMeta.IJKM_KEY_TYPE, currentTylp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        parms = getParms();
        parms.put("data", AesEncryptionUtil.encrypt(jsonObject.toString(), Constant.AES_PWD, Constant.AES_IV));
        StringBuilder sb = new StringBuilder("");
        for (Map.Entry<String, String> entry : parms.entrySet()) {
            sb.append("&").append((String) entry.getKey()).append("=").append(EncodeUtils.urlEncode((String) entry.getValue(), Key.STRING_CHARSET_NAME));
        }
        parms.put("sig", EncryptUtils.encryptMD5ToString(sb.toString().substring(1) + Constant.URL_SIG_KEY));
        return parms;
    }


}
