/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.constant;

/**
 * Created by jm1138 on 2017/5/24.
 * 常量配置类
 */
public class ConfigConst {
    public static final String COLON = ":"; // 冒号
    public static final String SPACE = " "; // 空格
    public static final String QUESTION_MARK = "?"; // 问号
    public static final String OBLIQUE = "/"; // 斜杠
    public static final String IS_EQUAL_TO = "="; // 等号

    // 默认文本文件编码字符集
    public static final String CHARSET_ASCII = "ASCII";
    // 默认网络传输编码字符集
    public static final String CHARSET_UTF8 = "UTF-8";
    // http头配置
    public static final String CONTENT_TYPE = "Content-Type";
    // 连接尝试遭到拒绝。
    public static final int ERR_CONNECTION_REFUSED = -1100;

    // JsApiTicketDTO 缓存json文件
    public static final String JS_API_TICKET_CONFIG_JSON = "json/js-api-ticket.json";
    // AccessTokenDTO 缓存json文件
    public static final String ACCESS_TOKEN_CONFIG_JSON = "json/access_token.json";

    // 获取ticket的url
    public static final String QY_TICKET_URL = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=";
    public static final String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={0}&type=jsapi";

    // 获取access_token的url
    public static final String QY_ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={0}&corpsecret={1}";
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";

    public static final String SIGN_STR = "jsapi_ticket={0}&noncestr={1}&timestamp={2}&url={3}";
    public static final String ACCESS_TOKEN_STRING = "access_token";
    public static final String TICKET_STRING = "ticket";
}
