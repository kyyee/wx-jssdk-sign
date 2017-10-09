/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.constant;

/**
 * Created by jm1138 on 2017/6/16.
 * 错误码
 */
public enum ErrorCodeEnum {
    SIGN_EXCEPTION(-500, "签名异常。"),
    NO_SUCH_ALGORITHM_EXCEPTION(-500, "未找到合适算法异常。"),
    UNSUPPORTED_ENCODING_EXCEPTION(-500, "不支持的解码类型异常。"),
    IO_EXCEPTION(-500, "IO异常。"),
    REST_CLIENT_EXCEPTION(-500, "请求超时或其他Http请求异常。"),
    NULL_POINTER_EXCEPTION(-500, "空指针异常。"),
    UNKNOWN_EXCEPTION(-500, "未明确的异常。");

    private final int code;
    private final String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    @Override
    public String toString() {
        return "ErrorCodeEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
