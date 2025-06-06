package com.admin.mapper;

import com.admin.dto.GoodsPageDTO;
import com.admin.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chaoheng
 * @since 2025-05-30
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    // 分页查询商品列表
    IPage<Goods> selectGoodsPage(Page<Goods> page, @Param("query") GoodsPageDTO query);

    /**
     * 根据ID查询商品
     * @param goodId 商品ID
     * @return 商品实体
     */
    @Select("SELECT * FROM goods WHERE good_id = #{goodId}")
    Goods selectGoodsById(@Param("goodId") Integer goodId);

    @Insert("INSERT INTO goods(good_name, good_type, good_price, " +
            "good_date, good_state, good_stock, create_time) " +
            "VALUES(#{goodName}, #{goodType}, #{goodPrice}, " +
            "#{goodDate}, #{goodState}, #{goodStock}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "goodId")
    int insertGoods(Goods goods);

    boolean existsByName(String goodName);

    /**
     * 根据商品ID删除商品
     * @param goodId 商品ID
     * @return 删除的行数
     */
    @Delete("DELETE FROM goods WHERE good_id = #{goodId}")
    int deleteByGoodId(@Param("goodId") Integer goodId);
}
