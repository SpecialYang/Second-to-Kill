package com.specialyang.secondstokill.service;

import java.util.Date;

import com.specialyang.secondstokill.controller.vo.GoodsVo;
import com.specialyang.secondstokill.dao.OrderDAO;
import com.specialyang.secondstokill.domain.Order;
import com.specialyang.secondstokill.domain.SecondToKillOrder;
import com.specialyang.secondstokill.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
	
	@Autowired
	OrderDAO orderDAO;
	
	public SecondToKillOrder getSecondToKillOrderByUserIdGoodsId(long userId, long goodsId) {
		return orderDAO.getSecondToKillOrderByUserIdGoodsId(userId, goodsId);
	}

	@Transactional
	public Order createOrder(User user, GoodsVo goods) {
		Order order = new Order();
		order.setCreateDate(new Date());
		order.setDeliveryAddrId(0L);
		order.setGoodsCount(1);
		order.setGoodsId(goods.getId());
		order.setGoodsName(goods.getGoodsName());
		order.setGoodsPrice(goods.getSecondToKillPrice());
		order.setOrderChannel(1);
		order.setStatus(0);
		order.setUserId(user.getId());
		long orderId = orderDAO.insert(order);

		SecondToKillOrder secondToKillOrder = new SecondToKillOrder();
		secondToKillOrder.setGoodsId(goods.getId());
		secondToKillOrder.setOrderId(orderId);
		secondToKillOrder.setUserId(user.getId());
		orderDAO.insertSecondToKillOrder(secondToKillOrder);
		return order;
	}
	
}
