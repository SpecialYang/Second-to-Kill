package com.specialyang.secondstokill.controller;

import com.specialyang.secondstokill.controller.vo.GoodsVo;
import com.specialyang.secondstokill.domain.Order;
import com.specialyang.secondstokill.domain.User;
import com.specialyang.secondstokill.entity.CodeMsg;
import com.specialyang.secondstokill.service.GoodsService;
import com.specialyang.secondstokill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Fan Yang in 2018/12/7 9:28 AM.
 */
@Controller
public class SecondToKillController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @RequestMapping("/secondtokill/{id}")
    public String secondToKill(Model model,
                               User user,
                               @PathVariable("id") long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            return "login";
        }

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAOSHA_OVER.getMsg());
            return "miaosha_fail";
        }
        Order order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }

        //减库存，下订单，写入秒杀订单
    }
}
