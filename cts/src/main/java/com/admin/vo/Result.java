package com.admin.vo;

import com.admin.entity.Orders;
import com.admin.enums.HTTPCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 统一API响应封装类
 * 用于标准化控制器返回结果
 */
@Data
@AllArgsConstructor
public class Result<T> {
    // 状态码：0-成功，非0-失败
    private int code;

    // 操作描述信息
    private String msg;

    // 返回的业务数据
    private T data;

    // 默认成功方法
    public static <T> Result<T> ok() {
        return new Result<>(0, "success", null);
    }

    // 带数据的成功响应
    public static <T> Result<T> ok(T data) {
        return new Result<>(200, "success", data);
    }

    // 默认失败方法
    public static <T> Result<T> fail(HTTPCodeEnum codeEnum) {
        return new Result<>(codeEnum.getCode(), codeEnum.getMsg(), null);
    }

    public static <T> Result<T> ok(HTTPCodeEnum codeEnum) {
        return new Result<>(codeEnum.getCode(), codeEnum.getMsg(), null);
    }
    public static <T> Result<T> success(HTTPCodeEnum codeEnum) {
        return new Result<>(codeEnum.getCode(), codeEnum.getMsg(), null);
    }


    public static <T> Result<T> fail(String msg) {
        {
            return new Result<>(1, msg, null);
        }
    }

    // 带错误码和消息的失败响应
    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    // 带数据的高级成功响应
    public static <T> Result<T> success(int code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    public static <T> Result<T> success(int code, T data) {
        return new Result<>(code, "success",data);
    }


    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

}