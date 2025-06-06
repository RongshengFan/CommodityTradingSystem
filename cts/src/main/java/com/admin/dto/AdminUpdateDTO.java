package com.admin.dto;

import lombok.Data;
@Data
public class AdminUpdateDTO {
    private String username;

    private String avatar;

    private String phone;

    private String email;



    private String password; // 新密码


    private String oldPassword; // 原始密码用于验证
}
