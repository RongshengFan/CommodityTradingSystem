package com.admin.controller;

import com.admin.dto.UserPageDTO;
import com.admin.entity.User;
import com.admin.enums.HTTPCodeEnum;
import com.admin.service.IUserService;
import com.admin.vo.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author chaoheng
 * @since 2025-06-06
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 分页查询用户列表
     */
    @PostMapping("/list")
    public Result<IPage<User>> listUsers(@RequestBody UserPageDTO dto) {
        // 设置默认分页参数
        if (dto.getSize() <= 0) dto.setSize(10);
        if (dto.getCurrent() <= 0) dto.setCurrent(1);

        IPage<User> pageResult = userService.listUsersByPage(dto);
        return Result.success(pageResult);
    }


    /**
     * 禁用用户
     */
    @PutMapping("/disable/{userId}")
    public Result<Void> disableUser(@PathVariable String userId) {
        userService.disableUser(userId);
        return Result.success(HTTPCodeEnum.SUCCESS);
    }

    /**
     * 启用用户
     */
    @PutMapping("/enable/{userId}")
    public Result<Void> enableUser(@PathVariable String userId) {
        userService.enableUser(userId);
        return Result.success(HTTPCodeEnum.SUCCESS);
    }

    /**
     * 切换用户状态（禁用/启用）
     */
    @PutMapping("/toggle/{userId}")
    public Result<Byte> toggleUserStatus(@PathVariable String userId) {
        Byte newStatus = userService.toggleUserStatus(userId);
        return Result.success(HTTPCodeEnum.SUCCESS);
    }

}
