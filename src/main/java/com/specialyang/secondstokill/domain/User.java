package com.specialyang.secondstokill.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by Fan Yang in 2018/12/4 1:56 PM.
 */
@Data
public class User {

    private Long id;

    private String nickname;

    private String password;

    private String salt;

    private String head;

    private Date registerDate;

    private Date lastLoginDate;

    private Integer loginCount;

    public User() {}

    public User(String mobile, String password, String salt) {
        this.id = Long.parseLong(mobile);
        this.password = password;
        this.salt = salt;
    }
}
