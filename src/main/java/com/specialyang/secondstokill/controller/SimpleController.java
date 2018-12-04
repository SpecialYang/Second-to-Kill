package com.specialyang.secondstokill.controller;

import com.specialyang.secondstokill.domain.User;
import com.specialyang.secondstokill.entity.Response;
import com.specialyang.secondstokill.service.UserService;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Fan Yang in 2018/12/4 10:19 AM.
 */
@Controller
@RequestMapping("/demo")
public class SimpleController {

    @Autowired
    UserService userService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "SpecialYang");
        return "hello";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @RequestMapping("/db/user/{id}")
    @ResponseBody
    public Response<User> user(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        return Response.success(user);
    }

    /**
     * 测试事务
     * @return
     */
    @RequestMapping("/db/tx")
    @ResponseBody
    public Response<Boolean> tx() {
        userService.tx();
        return Response.success(true);
    }
}
