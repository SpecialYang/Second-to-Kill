package com.specialyang.secondstokill.service;

import com.specialyang.secondstokill.controller.vo.GoodsVo;
import com.specialyang.secondstokill.domain.Order;
import com.specialyang.secondstokill.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecondToKillService {
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;

	@Transactional
	public Order miaosha(User user, GoodsVo goods) {
		//减库存 下订单 写入秒杀订单
		goodsService.reduceStock(goods);
		return orderService.createOrder(user, goods);
	}
	
}
