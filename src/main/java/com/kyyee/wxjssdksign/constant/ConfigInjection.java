/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */
package com.kyyee.wxjssdksign.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by jm1138 on 2017/8/14.
 * 通过application.properties文件注入配置的配置类。
 */
@Component
@ConfigurationProperties(prefix = "wjsConfig")
public class ConfigInjection {
    // 微信appId
    private String appId;
    // 微信appSecret
    private String appSecret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
