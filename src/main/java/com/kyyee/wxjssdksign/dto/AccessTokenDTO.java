/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jm1138 on 2017/10/9.
 */
public class AccessTokenDTO {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expire_time")
    private long expireTime;

    public AccessTokenDTO() {
    }

    public AccessTokenDTO(String accessToken, long expireTime) {
        this.accessToken = accessToken;
        this.expireTime = expireTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "AccessTokenDTO{" +
                "accessToken='" + accessToken + '\'' +
                ", expireTime='" + expireTime + '\'' +
                '}';
    }
}
