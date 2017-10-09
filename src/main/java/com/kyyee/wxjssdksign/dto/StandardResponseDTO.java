/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.dto;

/**
 * Created by jm1138 on 2017/6/13.
 * 标准响应体结构
 */
public class StandardResponseDTO<T> {
    // 版本号
    private final static int DEFAULT_VERSION = 100;

    private int version;
    private int result;
    private String message;
    private T data;

    public StandardResponseDTO() {
    }

    // 成功时返回
    public StandardResponseDTO(int result, T data) {
        this(DEFAULT_VERSION, result, "", data);
    }

    // 失败时返回
    public StandardResponseDTO(int result, String message, T data) {
        this(DEFAULT_VERSION, result, message, data);
    }

    // 成功时自定义版本返回
    public StandardResponseDTO(int version, int result, T data) {
        this(version, result, "", data);
    }

    // 失败时自定义版本返回
    public StandardResponseDTO(int version, int result, String message, T data) {
        this.version = version;
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StandardResponseDTO{" +
                "version=" + version +
                ", result=" + result +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
