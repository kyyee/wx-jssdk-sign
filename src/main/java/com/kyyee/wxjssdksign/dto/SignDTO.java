/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.dto;

/**
 * Created by jm1138 on 2017/10/9.
 */
public class SignDTO {
    private String appId;        // 微信appId
    private String nonceStr;     // 随机字符串
    private String timestamp;    // 时间戳
    private String url;          // 需授权的url
    private String rawSignature; // 为处理的签名
    private String signature;    // 处理后的签名

    public SignDTO() {
    }

    public SignDTO(String appId, String nonceStr, String timestamp, String url, String rawSignature, String signature) {
        this.appId = appId;
        this.nonceStr = nonceStr;
        this.timestamp = timestamp;
        this.url = url;
        this.rawSignature = rawSignature;
        this.signature = signature;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRawSignature() {
        return rawSignature;
    }

    public void setRawSignature(String rawSignature) {
        this.rawSignature = rawSignature;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "SignDTO{" +
                "appId='" + appId + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", url='" + url + '\'' +
                ", rawSignature='" + rawSignature + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
