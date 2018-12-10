package com.specialyang.secondstokill.dao;

import java.util.List;
import com.specialyang.secondstokill.controller.vo.GoodsVo;
import com.specialyang.secondstokill.domain.SecondToKillGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface GoodsDAO {

	@Select("select g.*, sg.stock_count, sg.start_date, sg.end_date, sg.price " +
			"from second_to_kill_goods sg left join goods g on sg.goods_id = g.id")
	public List<GoodsVo> listGoodsVo();

	@Select("select g.*, sg.stock_count, sg.start_date, sg.end_date, sg.price " +
			"from second_to_kill_goods sg left join goods g on sg.goods_id = g.id " +
			"where g.id = #{goodsId}")
	public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

	@Update("update second_to_kill_goods set stock_count = stock_count - 1 " +
			"where goods_id = #{goodsId}")
	public int reduceStock(SecondToKillGoods secondToKillGoods);
	
}
