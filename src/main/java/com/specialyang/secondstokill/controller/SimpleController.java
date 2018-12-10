package com.specialyang.secondstokill.controller;

import com.specialyang.secondstokill.domain.User;
import com.specialyang.secondstokill.entity.Response;
import com.specialyang.secondstokill.service.UserService;
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


    @RequestMapping("/redis/get/id/{id}")
    @ResponseBody
    public Response<User> redisGet(@PathVariable("id") long id) {
        User result = userService.getUserByIdFromRedis(id);
        return Response.success(result);
    }

    @RequestMapping("/redis/get/name/{name}")
    @ResponseBody
    public Response<User> redisGet(@PathVariable("name") String name) {
        User result = userService.getUserByNameFromRedis(name);
        return Response.success(result);
    }

    @RequestMapping("/redis/set/user")
    @ResponseBody
    public Response<User> setUserById() {
        User user = new User();
        user.setId((long) 10);
        user.setNickname("haha");
        userService.addUserByIdIntoRedis(user);
        userService.addUserByNameIntoRedis(user);
        User result = userService.getUserByIdFromRedis(user.getId());
        return Response.success(result);
    }
}
