package com.admin.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author chaoheng
 * @since 2025-05-30
 */
// Adminer.java
@Data
@TableName("adminer")
public class Adminer implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String username;

    @JsonIgnore // 返回数据时忽略密码字段
    @TableField("password")
    private String password;

    @TableField("avatar")
    private String avatar;

    @TableField("phone")
    private String phone;

    @TableField("email")
    private String email;

    @TableField("role")
    private String role; // 角色标识

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
}