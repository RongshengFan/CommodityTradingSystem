package com.shanzhu.em.service;

import com.shanzhu.em.entity.Good;

import java.util.List;

/**
 * 推荐服务
 *
 * @author: ShanZhu
 * @date: 2024-07-26 15:27:13
 */
public interface RecommendService {

    /**
     * 推荐用户商品
     *
     * @param userId 用户id
     * @return 推荐商品
     */
    List<Good> recommendGoods(Long userId);
}

  