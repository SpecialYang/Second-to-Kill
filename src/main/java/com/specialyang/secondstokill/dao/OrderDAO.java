package com.specialyang.secondstokill.dao;

import com.specialyang.secondstokill.domain.Order;
import com.specialyang.secondstokill.domain.SecondToKillOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface OrderDAO {
	
	@Select("select * from second_to_kill_order where user_id=#{userId} and goods_id=#{goodsId}")
	public SecondToKillOrder getSecondToKillOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

	@Insert("insert into `order`(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
			+ "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
	@SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
	public long insert(Order order);
	
	@Insert("insert into second_to_kill_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
	public int insertSecondToKillOrder(SecondToKillOrder secondToKillOrder);

	
}
