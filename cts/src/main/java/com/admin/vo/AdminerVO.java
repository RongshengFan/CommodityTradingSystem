package com.admin.vo;

import lombok.Data;

import java.sql.Date;

// AdminerVO.java
@Data
public class AdminerVO {
    private Long id;
    private String username;
    private String avatar;
    private String phone;
    private String email;
    private String role;
    private Date createTime;
}
