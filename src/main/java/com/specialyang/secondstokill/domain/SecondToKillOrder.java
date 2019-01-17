package com.specialyang.secondstokill.domain;

import lombok.Data;

/**
 * Created by Fan Yang in 2018/12/10 7:32 PM.
 */
@Data
public class SecondToKillOrder {

    private Long id;

    private Long userId;

    private Long  orderId;

    private Long goodsId;
}
