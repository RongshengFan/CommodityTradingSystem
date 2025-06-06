package com.admin.dto;

import lombok.Data;

// GoodsPageDTO.java
@Data
public class GoodsPageDTO {

    private Integer page = 1;

    private Integer size = 10;


    private String nameKeyword;


    private String type;


    private String state;
}