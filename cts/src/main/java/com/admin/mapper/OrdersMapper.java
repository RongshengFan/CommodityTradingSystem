package com.admin.mapper;

import com.admin.dto.OrdersPageDTO;
import com.admin.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chaoheng
 * @since 2025-05-30
 */
public interface OrdersMapper extends BaseMapper<Orders> {

    /**
     * 分页查询订单列表
     * @param page 分页对象
     * @param query 查询条件
     * @return 分页结果
     */
    @Select({
            "<script>",
            "SELECT * ",
            "FROM orders ",
            "<where>",
            "   <if test='query.orderState != null and query.orderState != \"\"'>",
            "       AND order_state = #{query.orderState}",
            "   </if>",
            "</where>",
            "ORDER BY order_time DESC",
            "</script>"
    })
    IPage<Orders> selectOrderPage(Page<Orders> page, @Param("query") OrdersPageDTO query);

    @Select("SELECT * FROM orders WHERE user_id = #{userId} ORDER BY order_time DESC")
    List<Orders> selectOrdersByUserId(@Param("userId") String userId);

    /**
     * 更新订单状态
     * @param orderId 订单ID
     * @param newState 新状态（"已发货"）
     * @return 更新条数
     */
    @Update("UPDATE orders SET order_state = #{newState} WHERE order_id = #{orderId}")
    int updateOrderState(@Param("orderId") String orderId,
                         @Param("newState") String newState);


}
