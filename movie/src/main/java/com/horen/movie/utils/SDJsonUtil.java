package com.horen.movie.utils;

import com.alibaba.fastjson.JSON;

import java.util.Map;

public class SDJsonUtil {
    private SDJsonUtil() {
    }

    public static <T> T json2Object(String json, Class<T> clazz) {
        return (T) JSON.parseObject(json, (Class) clazz);
    }

    public static String object2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T map2Object(Map map, Class<T> clazz) {
        if (map != null) {
            return json2Object(object2Json(map), clazz);
        }
        return null;
    }
}
