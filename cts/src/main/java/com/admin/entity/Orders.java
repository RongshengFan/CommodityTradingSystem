package com.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author chaoheng
 * @since 2025-05-30
 */
@Getter
@Setter
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId("order_id")
    private String orderId;
    private String goodId;
    private String goodName;
    private String userId;
    private String userName;
    /**
     * 下单数量
     */
    private String orderCount;
    /**
     * 订单金额
     */
    private String orderCost;
    /**
     * 收货地址
     */
    private String orderAddr;
    /**
     * 下单时间
     */
    private String orderTime;
    /**
     * 订单状态：未发货，已发货，已收货
     */
    private String orderState;
}
