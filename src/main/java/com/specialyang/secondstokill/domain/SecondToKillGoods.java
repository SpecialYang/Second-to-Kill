package com.specialyang.secondstokill.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by Fan Yang in 2018/12/6 9:56 PM.
 */
@Data
public class SecondToKillGoods {

    private long id;

    private long goodsId;

    private int stockCount;

    private Date startDate;

    private Date endDate;
}
