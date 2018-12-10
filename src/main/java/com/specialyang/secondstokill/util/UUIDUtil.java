package com.specialyang.secondstokill.util;

import java.util.UUID;

/**
 * Created by Fan Yang in 2018/12/6 7:28 PM.
 */
public class UUIDUtil {

    public static String generatorRandomId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
