package com.specialyang.secondstokill.controller;

import com.specialyang.secondstokill.domain.User;
import com.specialyang.secondstokill.entity.CodeMsg;
import com.specialyang.secondstokill.entity.Response;
import com.specialyang.secondstokill.service.UserService;
import com.specialyang.secondstokill.util.ValidatorUtil;
import com.specialyang.secondstokill.controller.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by Fan Yang in 2018/12/6 8:57 AM.
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Response<Boolean> login(HttpServletResponse response, @Valid LoginVo loginVo) {
        logger.info("请求的参数为：{}", loginVo);
        //登录
        userService.login(response, loginVo);
        return Response.success(true);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Response<User> register(@RequestParam(value = "mobile") String mobile,
                                   @RequestParam(value = "nickname") String nickname,
                                   @RequestParam(value = "password") String password) {
        if (StringUtils.isEmpty(mobile)) {
            return Response.error(CodeMsg.MOBILE_EMPTY);
        }
        if (StringUtils.isEmpty(nickname)) {
            return Response.error(CodeMsg.NICKNAME_EMPTY);
        }
        if (StringUtils.isEmpty(password)) {
            return Response.error(CodeMsg.PASSWORD_EMPTY);
        }
        if (!ValidatorUtil.isMobile(mobile)) {
            return Response.error(CodeMsg.MOBILE_ERROR);
        }
        User user = userService.addUser(mobile, nickname, password);
        return Response.success(user);
    }
}
