package com.specialyang.secondstokill.domain;

import lombok.Data;

/**
 * Created by Fan Yang in 2018/12/6 9:54 PM.
 */
@Data
public class Goods {

    private long id;

    private String goodsName;

    private String goodsTitle;

    private String goodsImg;

    private String goodsDetail;

    private Double goodsPrice;

    private int goodsStock;
}
