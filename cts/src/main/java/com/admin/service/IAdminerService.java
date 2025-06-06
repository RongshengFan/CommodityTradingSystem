package com.admin.service;

import com.admin.dto.AdminUpdateDTO;
import com.admin.entity.Adminer;
import com.admin.vo.AdminerVO;
import com.admin.vo.PageResult;
import com.admin.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chaoheng
 * @since 2025-05-30
 */
public interface IAdminerService extends IService<Adminer> {
    /**
     * 用户注册
     * @param user 用户对象
     * @return 注册结果
     */
    Result<String> register(Adminer user);
    /**
     * 管理员登录
     * @param username 用户名
     * @param password 密码
     * @return 登录成功的Adminer对象
     */
    Adminer login(String username, String password);
    PageResult<Adminer> getAdminerPage(Integer page, Integer size, String username);
    AdminerVO getAdminerDetail(Long id);
    /**
     * 更新管理员信息（包含密码）
     * @param adminId 当前管理员ID
     * @param updateDTO 更新信息DTO
     * @return 更新后的管理员信息
     */
    Adminer updateAdminInfo(Long adminId, AdminUpdateDTO updateDTO);
}
