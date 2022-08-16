/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */
package com.kyyee.wxjssdksign.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by jm1138 on 2017/8/14.
 * 通过application.properties文件注入配置的配置类。
 */
@Component
@ConfigurationProperties(prefix = "wx")
@Data
public class WxProperties {
    // 微信appId
    private String appId;
    // 微信appSecret
    private String appSecret;
}
