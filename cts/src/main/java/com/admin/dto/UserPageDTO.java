// src/main/java/com/admin/dto/UserPageDTO.java
package com.admin.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.admin.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageDTO extends Page<User> {
    /**
     * 用户名关键词
     */
    private String username;

    /**
     * 手机号关键词
     */
    private String phone;

    /**
     * 用户状态: 0-禁用，1-启用
     */
    private Byte status;
}