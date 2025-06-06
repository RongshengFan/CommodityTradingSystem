package com.admin.controller;

import com.admin.dto.GoodsCreateDTO;
import com.admin.dto.GoodsPageDTO;
import com.admin.enums.HTTPCodeEnum;
import com.admin.service.IGoodsService;
import com.admin.vo.GoodsDetailVO;
import com.admin.vo.GoodsVO;
import com.admin.vo.PageResult;
import com.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chaoheng
 * @since 2025-05-30
 */
// GoodsController.java
@RestController
@RequestMapping("/admin/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @GetMapping("/page")
    public Result<PageResult<GoodsVO>> getGoodsPage( GoodsPageDTO query) {
        PageResult<GoodsVO> result = goodsService.getGoodsPage(query);
        return Result.success(result);
    }

    @GetMapping("/{goodId}")
    public Result<GoodsDetailVO> getGoodsDetail(
            @PathVariable  Integer goodId) {
        GoodsDetailVO detail = goodsService.getGoodsDetailById(goodId);
        return Result.success(detail);
    }
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<GoodsDetailVO> createGoods(
            @Validated GoodsCreateDTO createDTO,
            BindingResult bindingResult
    ) {
        // 1. 参数校验
        if (bindingResult.hasErrors()) {
            return Result.fail(400, bindingResult.getFieldError().getDefaultMessage());
        }

        // 2. 调用服务
        GoodsDetailVO vo = goodsService.createGoods(createDTO);
        return Result.success(vo);
    }

    /**
     * 根据商品ID删除商品
     * @param goodId 商品ID
     * @return 操作结果
     */
    @DeleteMapping("/{goodId}")
    public Result<Void> deleteGoodById(@PathVariable Integer goodId) {
        boolean success = goodsService.deleteGoodById(goodId);

        if (success) {
            return Result.ok(HTTPCodeEnum.SUCCESS);
        } else {
            return Result.fail(HTTPCodeEnum.SERVER_ERROR);
        }
    }




}
