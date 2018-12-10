package com.specialyang.secondstokill.redis.key;

/**
 * Created by Fan Yang in 2018/12/5 8:09 PM.
 */
public interface PrefixKey<T> {

    String getPrefixKey();

    Class<T> getValueType();

    int expireSeconds();
}
