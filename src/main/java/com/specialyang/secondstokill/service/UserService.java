package com.specialyang.secondstokill.service;

import com.specialyang.secondstokill.dao.UserDAO;
import com.specialyang.secondstokill.domain.User;
import com.specialyang.secondstokill.entity.CodeMsg;
import com.specialyang.secondstokill.exception.GlobalException;
import com.specialyang.secondstokill.redis.RedisAdapter;
import com.specialyang.secondstokill.redis.key.UserKey;
import com.specialyang.secondstokill.util.MD5Util;
import com.specialyang.secondstokill.controller.vo.LoginVo;
import com.specialyang.secondstokill.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Fan Yang in 2018/12/4 1:59 PM.
 */
@Service
public class UserService {

    private static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    UserDAO userDAO;

    @Autowired
    RedisAdapter redisAdapter;

    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }


    public void addUserByIdIntoRedis(User user) {
        redisAdapter.set(UserKey.getById, user.getId(), user);
    }

    public void addUserByNameIntoRedis(User user) {
        redisAdapter.set(UserKey.getByName, user.getNickname(), user);
    }

    public User getUserByIdFromRedis(long id) {
        return redisAdapter.get(UserKey.getById, id);
    }

    public void addTokenIntoRedis(String token, User user) {
        redisAdapter.set(UserKey.token, token, user);
    }

    public User getTokenFromRedis(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return redisAdapter.get(UserKey.token, token);
    }

    public User getUserByNameFromRedis(String username) {
        return redisAdapter.get(UserKey.getByName, username);
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        User user = getUserById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }

        String dbPass = user.getPassword();
        String dbSalt = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, dbSalt);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token = UUIDUtil.generatorRandomId();
        addTokenIntoRedis(token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
        return true;
    }

    public User addUser(String mobile, String nickname, String password) {
        String dbSalt = MD5Util.generatorRandomSalt();
        String dbPass = MD5Util.formPassToDBPass(password, dbSalt);
        User user = new User();
        user.setId(Long.parseLong(mobile));
        user.setNickname(nickname);
        user.setPassword(dbPass);
        user.setSalt(dbSalt);
        user.setRegisterDate(new Date());
        user.setLastLoginDate(new Date());
        userDAO.insert(user);
        return user;
    }
}
