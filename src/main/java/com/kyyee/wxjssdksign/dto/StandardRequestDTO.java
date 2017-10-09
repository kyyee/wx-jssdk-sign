/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.dto;

/**
 * Created by jm1138 on 2017/6/19.
 * 标准请求结构
 *
 * @param <T> 请求内容data类型
 */
public class StandardRequestDTO<T> {
    // 版本号
    private final static int DEFAULT_VERSION = 100;

    private int version;

    private T data;

    public StandardRequestDTO() {
    }

    // 默认版本号
    public StandardRequestDTO(T data) {
        this(DEFAULT_VERSION, data);
    }

    public StandardRequestDTO(int version, T data) {
        this.version = version;
        this.data = data;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StandardRequestDTO{" +
                "version=" + version +
                ", data=" + data +
                '}';
    }
}
