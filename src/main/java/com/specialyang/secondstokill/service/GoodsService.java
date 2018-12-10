package com.specialyang.secondstokill.service;

import com.specialyang.secondstokill.controller.vo.GoodsVo;
import com.specialyang.secondstokill.dao.GoodsDAO;
import com.specialyang.secondstokill.domain.SecondToKillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Fan Yang in 2018/12/6 10:02 PM.
 */
@Service
public class GoodsService {

    @Autowired
    GoodsDAO goodsDAO;

    public List<GoodsVo> listGoodsVo(){
        return goodsDAO.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDAO.getGoodsVoByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVo goods) {
        SecondToKillGoods secondToKillGoods = new SecondToKillGoods();
        secondToKillGoods.setGoodsId(goods.getId());
        goodsDAO.reduceStock(secondToKillGoods);
    }

}
