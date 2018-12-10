package com.specialyang.secondstokill.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by Fan Yang in 2018/12/6 9:57 PM.
 */
@Data
public class Order {

    private long id;

    private long userId;

    private long goodsId;

    private long deliveryAddrid;

    private String goodsName;

    private int goodsCount;

    private double goodsPrice;

    private int orderChannel;

    private int status;

    private Date createDate;

    private Date payDate;
}
