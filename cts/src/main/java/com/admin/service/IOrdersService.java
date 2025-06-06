package com.admin.service;

import com.admin.dto.OrdersPageDTO;
import com.admin.entity.Orders;
import com.admin.vo.OrdersVO;
import com.admin.vo.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chaoheng
 * @since 2025-05-30
 */
public interface IOrdersService extends IService<Orders> {

    PageResult<OrdersVO> getOrderPage(OrdersPageDTO dto);
    List<Orders> getOrdersByUserId(String userId);
    /**
     * 更新订单状态为"已发货"
     * @param orderId 订单ID
     * @return 是否更新成功
     */
    boolean shipOrder(String orderId);


}
