package com.horen.horenbase.utils;

import com.horen.base.util.Utils;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author :ChenYangYi
 * @date :2018/06/29/14:34
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class ParmsUtils {

    private static Map<String, String> parms;

    public static Map<String, String> getParms() {
        parms = new TreeMap<>();
        parms.put("_sdk_version", Utils.getSDKVersion());
        parms.put("data", "10AA9CBC024CB8C0C87186486A98D1B64D073B555B7EE5D12394C258F67345C59A904B3FEEA68F4FD0534EFB95FEFE92");
        parms.put("ms_app_version", Utils.getAppVersionName(Utils.getAppContext()));
        parms.put("ms_device_name", Utils.getModel());
        parms.put("ms_deviceid", "865422032013740");
        parms.put("ms_system_version", Utils.getOSVersion());
        parms.put("sig", "ff500b4cde09344ee207e11583f8ac77");
        return parms;
    }
}
