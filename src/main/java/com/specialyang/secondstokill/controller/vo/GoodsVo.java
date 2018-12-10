package com.specialyang.secondstokill.controller.vo;

import com.specialyang.secondstokill.domain.Goods;
import lombok.Data;

import java.util.Date;

/**
 * Created by Fan Yang in 2018/12/6 10:03 PM.
 */
@Data
public class GoodsVo extends Goods {

    private double price;

    private int stockCount;

    private Date startDate;

    private Date endDate;

}
