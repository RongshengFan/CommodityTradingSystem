package com.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@Data
@TableName("goods")
@Accessors(chain = true)
public class Goods implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId("good_id")
    private Integer goodId;  // 商品ID
    private String goodName;  // 商品名称
    /**
     * 商品类型
     */
    private String goodType;

    @TableField("good_price")
    private BigDecimal goodPrice;  // 商品价格（改为BigDecimal）
    /**
     * 生产日期
     */
    @TableField("good_date")
    private String goodDate;
    /**
     * 状态：上架，下架
     */
    @TableField("good_state")
    private String goodState;
    /**
     * 库存
     */
    @TableField("good_stock")
    private Integer goodStock;
}