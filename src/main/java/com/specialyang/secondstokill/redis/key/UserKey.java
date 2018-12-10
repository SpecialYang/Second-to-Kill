package com.specialyang.secondstokill.redis.key;

import com.specialyang.secondstokill.domain.User;

/**
 * Created by Fan Yang in 2018/12/5 8:12 PM.
 */
public class UserKey extends BasePrefixKey<User> {

    private static final int TOKEN_EXPIRE_SECONDS = 3600 * 24 * 2;

    private UserKey(String prefix) {
        this(0, prefix);
    }

    private UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix, User.class);
    }

    public static UserKey getById = new UserKey("id");

    public static UserKey getByName = new UserKey("name");

    public static UserKey token = new UserKey(TOKEN_EXPIRE_SECONDS, "token");
}
