package com.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

// PageResult.java
@Data
@AllArgsConstructor
public class PageResult<T> {
    private List<T> records;
    private long total;
}