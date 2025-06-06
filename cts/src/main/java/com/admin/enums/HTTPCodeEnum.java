package com.admin.enums;

/**
 * 统一HTTP状态码枚举
 */
public enum HTTPCodeEnum {
    SUCCESS(0, "操作成功"),
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权访问"),
    FORBIDDEN(403, "访问被禁止"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_ERROR(500, "服务器内部错误"),
    SERVER_ERROR(406, "删除失败");

    private final int code;
    private final String msg;

    HTTPCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}