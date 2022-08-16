/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.common.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kyyee.wxjssdksign.exception.BaseErrorCode;
import com.kyyee.wxjssdksign.exception.IErrorCode;
import lombok.Data;

/**
 * Created by jm1138 on 2017/6/13.
 * 标准响应体结构
 */
@Data
public class ResponseDto<T> {
    private static final String SUCCESS_CODE = "0000000000";

    // 版本号
    private final static int DEFAULT_VERSION = 100;

    private int version = DEFAULT_VERSION;
    private String code = SUCCESS_CODE;
    private String message = "请求成功";
    private T data;

    public ResponseDto() {
    }

    public ResponseDto(T data) {
        this.data = data;
    }

    // 成功时返回
    public ResponseDto(String code, T data) {
        this(DEFAULT_VERSION, code, "", data);
    }

    // 失败时返回
    public ResponseDto(String code, String message, T data) {
        this(DEFAULT_VERSION, code, message, data);
    }

    // 成功时自定义版本返回
    public ResponseDto(int version, String code, T data) {
        this(version, code, "", data);
    }

    // 失败时自定义版本返回
    public ResponseDto(int version, String code, String message, T data) {
        this.version = version;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功时返回
     *
     * @return 消息结构体
     */
    public static ResponseDto<Object> success() {
        return new ResponseDto<Object>();
    }

    /**
     * 成功时返回
     *
     * @param data 消息body
     * @return 消息结构体
     */
    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<T>(data);
    }

    /**
     * 成功时返回
     *
     * @param data 消息body
     * @return 消息结构体
     */
    public static <T> ResponseDto<T> success(T data, String message) {
        return new ResponseDto<T>(DEFAULT_VERSION, SUCCESS_CODE, message, data);
    }

    /**
     * 失败时返回
     *
     * @return 消息结构体
     */
    public static ResponseDto<Void> error(String code, String message) {
        return new ResponseDto<Void>(code, message, null);
    }

    public static ResponseDto<Object> error(IErrorCode error) {
        return new ResponseDto<>(error.getCode(), error.getMessage());
    }

    public static ResponseDto<Object> of(BaseErrorCode baseErrorCode) {
        return new ResponseDto<>(baseErrorCode.getCode(), baseErrorCode.getMessage());
    }

    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }
}
