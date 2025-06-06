// OrdersVO.java
package com.admin.vo;

import lombok.Data;
import java.io.Serializable;

@Data
public class OrdersVO implements Serializable {
    private String orderId;       // 订单ID
    private String goodName;      // 商品名称
    private String userName;      // 用户名
    private String orderCount;    // 下单数量
    private String orderCost;     // 订单金额
    private String orderAddr;     // 收货地址
    private String orderTime;     // 下单时间
    private String orderState;    // 订单状态
}