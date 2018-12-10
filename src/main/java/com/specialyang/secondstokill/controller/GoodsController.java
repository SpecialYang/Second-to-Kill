package com.specialyang.secondstokill.controller;

import com.specialyang.secondstokill.controller.vo.GoodsVo;
import com.specialyang.secondstokill.domain.Goods;
import com.specialyang.secondstokill.domain.User;
import com.specialyang.secondstokill.service.GoodsService;
import com.specialyang.secondstokill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


/**
 * Created by Fan Yang in 2018/12/6 7:47 PM.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    UserService userService;

    @Autowired
    GoodsService goodsService;

    /**
     * 由argumentResolver 注入
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model,
                       User user) {
        model.addAttribute("user", user);
        //查询商品列表
        List<GoodsVo> goodsVoList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsVoList);
        return "goods_list";
    }

    @RequestMapping(value = "/good/{id}", method = RequestMethod.GET)
    public String detail(Model model,
                         User user,
                         @PathVariable("id") long goodsId) {
        model.addAttribute("user", user);
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int secondToKillStatus = 0;
        int remainSeconds = 0;
        if (now < startAt) {
            secondToKillStatus = 0;
            remainSeconds = (int) ((startAt - now) / 1000);
        } else if (now > endAt){
            secondToKillStatus = 2;
            remainSeconds = -1;
        } else {
            secondToKillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", secondToKillStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }
}
