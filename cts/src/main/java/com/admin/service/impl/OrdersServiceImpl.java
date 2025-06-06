package com.admin.service.impl;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chaoheng
 * @since 2025-05-30
 */
// OrdersServiceImpl.java
import com.admin.dto.OrdersPageDTO;
import com.admin.entity.Orders;
import com.admin.mapper.OrdersMapper;
import com.admin.service.IOrdersService;
import com.admin.vo.OrdersVO;
import com.admin.vo.PageResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>  implements IOrdersService {

    private final OrdersMapper ordersMapper;

    @Override
    public PageResult<OrdersVO> getOrderPage(OrdersPageDTO dto) {
        // 创建分页对象
        Page<Orders> page = new Page<>(dto.getPage(), dto.getSize());

        // 执行分页查询
        IPage<Orders> iPage = ordersMapper.selectOrderPage(page, dto);

        // 转换为VO列表
        List<OrdersVO> voList = iPage.getRecords().stream().map(order -> {
            OrdersVO vo = new OrdersVO();
            BeanUtils.copyProperties(order, vo); // 复制相同名称的属性

            // 添加额外处理的字段（如有需要）
            // vo.setOrderStateText(getStateText(order.getOrderState()));

            return vo;
        }).collect(Collectors.toList());

        // 封装分页结果
        return new PageResult<>(voList, iPage.getTotal());
    }

    @Override
    public List<Orders> getOrdersByUserId(String userId) {
        return ordersMapper.selectOrdersByUserId(userId);
    }

    @Override
    public boolean shipOrder(String orderId) {
        // 定义新状态
        final String SHIPPED_STATE = "已发货";

        // 执行更新
        int updatedCount = ordersMapper.updateOrderState(orderId, SHIPPED_STATE);

        // 返回是否成功更新一条记录
        return updatedCount == 1;
    }

}