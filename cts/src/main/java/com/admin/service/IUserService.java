package com.admin.service;

import com.admin.dto.UserPageDTO;
import com.admin.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author chaoheng
 * @since 2025-06-06
 */
public interface IUserService extends IService<User> {

    /**
     * 分页查询用户列表
     * @param dto 分页查询参数
     * @return 分页结果
     */
    IPage<User> listUsersByPage(UserPageDTO dto);

    /**
     * 禁用用户
     * @param userId 用户ID
     * @return 禁用是否成功
     */
    boolean disableUser(String userId);

    /**
     * 启用用户
     * @param userId 用户ID
     * @return 启用是否成功
     */
    boolean enableUser(String userId);

    /**
     * 切换用户状态（禁用/启用）
     * @param userId 用户ID
     * @return 切换后的状态
     */
    Byte toggleUserStatus(String userId);

}
