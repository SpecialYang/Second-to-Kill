package com.specialyang.secondstokill.redis;

import com.specialyang.secondstokill.redis.key.PrefixKey;
import com.specialyang.secondstokill.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Fan Yang in 2018/12/5 1:47 PM.
 */
@Service
public class RedisAdapter {

    @Autowired
    JedisPool jedisPool;

    public <T> T get(PrefixKey prefixKey, Object key) {
        Jedis jedis = null;
        T result = null;
        try {
            jedis = jedisPool.getResource();
            String strKey = prefixKey.getPrefixKey() + String.valueOf(key);
            Class<T> clazz = prefixKey.getValueType();
            String value = jedis.get(strKey);
            result = JSONUtil.deserializer(value, clazz);
        } finally {
            returnToPool(jedis);
        }
        return result;
    }

    /**
     * 增加或更新指定键的映射值
     * 键永不过期
     * @param key
     * @param value
     */
    public void set(PrefixKey prefixKey, Object key, Object value) {
        setex(prefixKey, key, 0, value);
    }

    /**
     * 对键设置过期时间，0代表永不过期
     * @param key
     * @param expireSeconds
     * @param value
     */
    public void setex(PrefixKey prefixKey, Object key, int expireSeconds,
                      Object value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String strKey = prefixKey.getPrefixKey() + String.valueOf(key);
            String textOfValue = JSONUtil.serilaizer(value);
            if (expireSeconds <= 0) {
                jedis.set(strKey, textOfValue);
            } else {
                jedis.setex(strKey, expireSeconds, textOfValue);
            }
        } finally {
            returnToPool(jedis);
        }
    }

    public boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } finally {
            returnToPool(jedis);
        }
    }

    public long incr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        } finally {
            returnToPool(jedis);
        }
    }

    public long decr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decr(key);
        } finally {
            returnToPool(jedis);
        }
    }

    public void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
