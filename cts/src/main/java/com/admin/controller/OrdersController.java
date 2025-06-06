package com.admin.controller;

import com.admin.dto.OrdersPageDTO;
import com.admin.entity.Orders;
import com.admin.service.IOrdersService;
import com.admin.vo.OrdersVO;
import com.admin.vo.PageResult;
import com.admin.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chaoheng
 * @since 2025-05-30
 */
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {

    private final IOrdersService ordersService;

    @GetMapping("/page")
    public Result<PageResult<OrdersVO>> getOrderPage(@Validated OrdersPageDTO dto) {
        PageResult<OrdersVO> result = ordersService.getOrderPage(dto);
        return Result.success(result);
    }
    @GetMapping("/user/{userId}")
    public Result<List<Orders>> getOrdersByUser(@PathVariable String userId) {
        List<Orders> orders = ordersService.getOrdersByUserId(userId);
        return Result.success(orders);
    }

    /**
     * 发货接口 - 更新订单状态为"已发货"
     * @param orderId 订单ID
     * @return 操作结果
     */
    @PutMapping("/{orderId}/ship")
    public Result<Void> shipOrder(@PathVariable String orderId) {
        boolean success = ordersService.shipOrder(orderId);

        if (success) {
            return Result.ok();
        } else {
            return Result.fail("订单不存在或状态更新失败");
        }
    }

}