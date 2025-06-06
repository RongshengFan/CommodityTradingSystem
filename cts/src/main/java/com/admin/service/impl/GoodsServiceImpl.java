package com.admin.service.impl;

import com.admin.dto.GoodsCreateDTO;
import com.admin.dto.GoodsPageDTO;
import com.admin.entity.Goods;
import com.admin.mapper.GoodsMapper;
import com.admin.service.IGoodsService;
import com.admin.vo.BusinessException;
import com.admin.vo.GoodsDetailVO;
import com.admin.vo.GoodsVO;
import com.admin.vo.PageResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chaoheng
 * @since 2025-05-30
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

        @Override
        public PageResult<GoodsVO> getGoodsPage(GoodsPageDTO query) {
            // 创建分页对象
            Page<Goods> pageInfo = new Page<>(query.getPage(), query.getSize());

            // 执行分页查询
            IPage<Goods> goodsPage = baseMapper.selectGoodsPage(pageInfo, query);

            // 转换为VO列表
            List<GoodsVO> voList = goodsPage.getRecords().stream()
                    .map(GoodsVO::fromEntity)
                    .collect(Collectors.toList());

            return new PageResult<>(voList, goodsPage.getTotal());
        }

    @Override
    public GoodsDetailVO getGoodsDetailById(Integer goodId) {
        Goods goods = baseMapper.selectGoodsById(goodId);
        if (goods == null) {
            throw new BusinessException("商品不存在");
        }
        return GoodsDetailVO.fromEntity(goods);
    }

    @Autowired
    private GoodsMapper goodsMapper;

    public GoodsDetailVO createGoods(GoodsCreateDTO dto) {
        // 1. 校验商品名称唯一性
        if (goodsMapper.existsByName(dto.getGoodName())) {
            throw new BusinessException("商品名称已存在");
        }

        // 3. 构建实体对象
        Goods goods = new Goods()
                .setGoodName(dto.getGoodName())
                .setGoodType(dto.getGoodType())
                .setGoodPrice(dto.getGoodPrice())
                .setGoodStock(dto.getGoodStock())
                .setGoodDate(dto.getGoodDate())
                .setGoodState(dto.getGoodState());

        // 4. 保存到数据库（事务保障）
        if (goodsMapper.insertGoods(goods) == 0) {
            throw new BusinessException("商品创建失败");
        }

        // 5. 返回带ID的VO对象
        return GoodsDetailVO.from(goods);
    }

    public boolean deleteGoodById(Integer goodId) {
        // 1. 验证商品是否存在
        Goods existingGood = goodsMapper.selectById(goodId);
        if (existingGood == null) {
            throw new BusinessException("商品不存在或已被删除");
        }

        // 2. 检查商品是否上架（已上架商品不能直接删除）
        if ("上架".equals(existingGood.getGoodState())) {
            throw new BusinessException("上架商品不能删除，请先下架");
        }

        // 3. 执行删除操作
        int deletedRows = goodsMapper.deleteByGoodId(goodId);

        // 4. 验证删除是否成功
        if (deletedRows == 1) {
            return true;
        }

        throw new BusinessException("商品删除失败，请稍后重试");
    }


}
