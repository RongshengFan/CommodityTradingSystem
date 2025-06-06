package com.admin.vo;
import com.admin.entity.Goods;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GoodsDetailVO {

    private Integer goodId;


    private String goodName;


    private String goodType;


    private String goodImg;


    private BigDecimal goodPrice;


    private String goodDate;


    private String goodState;


    private Integer goodStock;


    private LocalDateTime createTime;

    // 转换方法
    public static GoodsDetailVO fromEntity(Goods goods) {
        GoodsDetailVO vo = new GoodsDetailVO();
        BeanUtils.copyProperties(goods, vo);
        return vo;
    }
    public static GoodsDetailVO from(Goods goods) {
        GoodsDetailVO vo = new GoodsDetailVO();
        BeanUtils.copyProperties(goods, vo);
        return vo;
    }
}
