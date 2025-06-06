package com.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author chaoheng
 * @since 2025-06-06
 */
@Getter
@Setter
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @TableId("user_id")
    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 账户余额
     */
    private BigDecimal balance;
    /**
     * 注册时间
     */
    private LocalDateTime createTime;
    /**
     * 状态：0-禁用，1-启用
     */
    private Byte status;
}
