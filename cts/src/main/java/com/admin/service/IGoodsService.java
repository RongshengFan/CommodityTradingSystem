package com.admin.service;

import com.admin.dto.GoodsCreateDTO;
import com.admin.dto.GoodsPageDTO;
import com.admin.entity.Goods;
import com.admin.mapper.GoodsMapper;
import com.admin.vo.GoodsDetailVO;
import com.admin.vo.GoodsVO;
import com.admin.vo.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chaoheng
 * @since 2025-05-30
 */
public interface IGoodsService extends IService<Goods> {
    PageResult<GoodsVO> getGoodsPage(GoodsPageDTO query);

    /**
     * 根据ID查询商品详情
     * @param goodId 商品ID
     * @return 商品详情VO
     */
    GoodsDetailVO getGoodsDetailById(Integer goodId);


    GoodsDetailVO createGoods(GoodsCreateDTO dto);

    /**
     * 根据商品ID删除商品
     * @param goodId 商品ID
     * @return 是否删除成功
     */
    boolean deleteGoodById(Integer goodId);

}
