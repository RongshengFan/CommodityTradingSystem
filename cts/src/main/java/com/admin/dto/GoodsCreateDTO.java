package com.admin.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class GoodsCreateDTO {
    private String goodName;
    private String goodType;
    private BigDecimal goodPrice;
    private Integer goodStock;

    private String goodDate;
    private String goodState = "off_sale"; // 默认下架

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }
}
