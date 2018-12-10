package com.specialyang.secondstokill.redis.key;

/**
 * Created by Fan Yang in 2018/12/5 8:10 PM.
 */
public abstract class BasePrefixKey<T> implements PrefixKey<T> {

    private int expireSeconds;

    private String prefix;
    //value 的 值类型
    private Class<T> valueType;

    public BasePrefixKey(String prefix, Class<T> clazzType) {
        this(0, prefix, clazzType);
    }

    public BasePrefixKey(int expireSeconds, String prefix, Class<T> valueType) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
        this.valueType = valueType;
    }

    @Override
    public String getPrefixKey() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix + ":";
    }

    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public Class<T> getValueType() {
        return valueType;
    }
}
