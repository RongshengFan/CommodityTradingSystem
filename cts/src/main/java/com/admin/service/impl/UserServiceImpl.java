package com.admin.service.impl;

import com.admin.dto.UserPageDTO;
import com.admin.entity.User;
import com.admin.mapper.UserMapper;
import com.admin.service.IUserService;
import com.admin.vo.BusinessException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chaoheng
 * @since 2025-06-06
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private final UserMapper userMapper;


    @Override
    public IPage<User> listUsersByPage(UserPageDTO dto) {
        return userMapper.selectPageByDto(dto, dto);
    }


    @Override
    public boolean disableUser(String userId) {
        int result = userMapper.updateStatus(userId, (byte) 0);
        if (result == 0) {
            throw new BusinessException("禁用用户失败，用户不存在");
        }
        return true;
    }

    @Override
    public boolean enableUser(String userId) {
        int result = userMapper.updateStatus(userId, (byte) 1);
        if (result == 0) {
            throw new BusinessException("启用用户失败，用户不存在");
        }
        return true;
    }

    @Override
    public Byte toggleUserStatus(String userId) {
        // 1. 获取当前状态
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        // 2. 切换状态
        Byte newStatus = (byte) (user.getStatus() == 1 ? 0 : 1);
        userMapper.updateStatus(userId, newStatus);

        return newStatus;
    }

}
