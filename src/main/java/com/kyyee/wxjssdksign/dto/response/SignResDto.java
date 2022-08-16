/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.dto.response;

import lombok.Builder;
import lombok.Data;

/**
 * Created by jm1138 on 2017/10/9.
 */
@Data
@Builder
public class SignResDto {
    private String appId;        // 微信appId
    private String nonceStr;     // 随机字符串
    private String timestamp;    // 时间戳
    private String url;          // 需授权的url
    private String rawSignature; // 为处理的签名
    private String signature;    // 处理后的签名

}
