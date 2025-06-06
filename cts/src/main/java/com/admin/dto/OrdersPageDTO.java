package com.admin.dto;

import lombok.Data;
@Data
public class OrdersPageDTO {
    private Integer page = 1; // 默认第1页

    private Integer size = 10; // 默认每页10条

    // 订单状态：未发货(unshipped), 已发货(shipped), 已收货(received)
    private String orderState;
}