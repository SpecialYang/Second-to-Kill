package com.specialyang.secondstokill.util;

import com.alibaba.fastjson.JSON;

/**
 * Created by Fan Yang in 2018/12/5 7:21 PM.
 */
public class JSONUtil {

    public static <T> T deserializer(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    public static String serilaizer(Object object) {
        return JSON.toJSONString(object);
    }
}
