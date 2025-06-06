package com.admin.vo;

import com.admin.entity.Goods;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// GoodsVO.java
@Data
public class GoodsVO {
    private Long goodId;
    private String goodName;
    private String goodType;
    private String goodImg;
    private BigDecimal goodPrice;
    private Integer goodStock;
    private String goodState;
    private LocalDateTime createTime;

    // 转换方法
    public static GoodsVO fromEntity(Goods goods) {
        GoodsVO vo = new GoodsVO();
        BeanUtils.copyProperties(goods, vo);
        return vo;
    }
}
