package com.shanzhu.em.service.impl;

import com.google.common.collect.Lists;
import com.shanzhu.em.entity.Good;
import com.shanzhu.em.entity.Message;
import com.shanzhu.em.mapper.MessageMapper;
import com.shanzhu.em.service.GoodService;
import com.shanzhu.em.service.MessageService;
import com.shanzhu.em.service.RecommendService;
import com.shanzhu.em.utils.recommend.CoreMath;
import com.shanzhu.em.utils.recommend.dto.ProductDTO;
import com.shanzhu.em.utils.recommend.dto.RelateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 推荐服务
 *
 * @author: ShanZhu
 * @date: 2024-07-26 15:28:04
 */
@Slf4j
@Service
public class RecommendServiceImpl implements RecommendService {

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private GoodService goodService;

    @Override
    public List<Good> recommendGoods(Long userId) {
        CoreMath coreMath = new CoreMath();
        List<RelateDTO> relateDTOList = generateRelates();
        //执行推荐算法
        List<Long> recommendations = coreMath.recommend(userId, relateDTOList);
        List<ProductDTO> productDTOList = generateProducts().stream()
                .filter(e -> recommendations.contains(e.getProductId())).collect(Collectors.toList());

        List<Good> goods = new ArrayList<>();
        //获取商品Id
        List<Long> productIdList = productDTOList.stream().map(e -> e.getProductId()).collect(Collectors.toList());
        //根据商品Id获取商品
        for (Long productId : productIdList) {
            goods.add(goodService.getGoodById(productId));
        }
        return goods;
    }

    /**
     * 获取相关的所有评论数据
     *
     * @return 相关评论数据
     */
    private List<RelateDTO> generateRelates() {
        //查询所有商品的评论
        List<Message> messages = messageMapper.findAllMessage();
        List<RelateDTO> relateDTOList = Lists.newArrayList();
        for (Message message : messages) {
            RelateDTO relateDTO = new RelateDTO();
            relateDTO.setUserId(message.getUserId());
            relateDTO.setProductId(message.getGoodId());
            relateDTO.setIndex(message.getScore());
            relateDTOList.add(relateDTO);
        }
        if (CollectionUtils.isEmpty(relateDTOList)) {
            log.info("--------------------List<RelateDTO>为空！");
        }

        return relateDTOList;
    }

    /**
     * 获取相关的所有商品数据
     *
     * @return 相关商品数据
     */
    private List<ProductDTO> generateProducts() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Good> goods = goodService.list();
        for (Good good : goods) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(good.getId());
            productDTO.setProductPrice(good.getSaleMoney().toString());
            productDTOList.add(productDTO);
        }
        if (CollectionUtils.isEmpty(productDTOList)) {
            log.info("----------------------List<ProductDTO>为空！");
        }
        return productDTOList;
    }

}

  