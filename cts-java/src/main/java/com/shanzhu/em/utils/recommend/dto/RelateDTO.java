package com.shanzhu.em.utils.recommend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 关联对象信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelateDTO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 业务id
     */
    private Long productId;

    /**
     * 关联指数
     */
    private Integer index;

}
