/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyyee.wxjssdksign.constant.ConfigConst;
import com.kyyee.wxjssdksign.constant.ConfigInjection;
import com.kyyee.wxjssdksign.dto.AccessTokenDTO;
import com.kyyee.wxjssdksign.dto.JsApiTicketDTO;
import com.kyyee.wxjssdksign.dto.SignDTO;
import com.kyyee.wxjssdksign.exception.SignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Formatter;
import java.util.UUID;

/**
 * Created by jm1138 on 2017/9/7.
 */
@Component
public class SignServiceImpl implements SignService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignServiceImpl.class);

    @Resource
    private
    RestTemplate restTemplate;

    @Resource
    private
    ConfigInjection configInjection;

    @Override
    public SignDTO sign(String url) throws NoSuchAlgorithmException, IOException, SignException {
        String jsApiTicket = getJsApiTicket();
        String nonceStr = createNonceStr();
        String timestamp = createTimestamp();
        String rawSignature;
        String signature;

        // 注意这里参数名必须全部小写，且参数的顺序要按照 key 值 ASCII 码升序排序
        rawSignature = MessageFormat.format(ConfigConst.SIGN_STR, jsApiTicket, nonceStr, timestamp, url);
        LOGGER.info(rawSignature);

        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(rawSignature.getBytes(ConfigConst.CHARSET_UTF8));
        signature = byteToHex(crypt.digest());

        return new SignDTO(configInjection.getAppId(), nonceStr, timestamp, url, rawSignature, signature);
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private String createNonceStr() {
        return UUID.randomUUID().toString();
    }

    private String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    private long getCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }

    private long getExpireTime() {
        return System.currentTimeMillis() / 1000 + 7000;
    }


    private String getJsApiTicket() throws IOException, SignException {
        // ticket 应该全局存储与更新，以下代码以写入到文件中做示例，可写入数据库
        JsApiTicketDTO jsApiTicketDTO = readJsApiTicketFromFile(ConfigConst.JS_API_TICKET_CONFIG_JSON);
        if (jsApiTicketDTO.getExpireTime() < getCurrentTime()) {
            String accessToken = getAccessToken();
            // 如果是企业号用以下 URL 获取 ticket
            // String url = ConfigConst.QY_TICKET_URL;
            String url = MessageFormat.format(ConfigConst.TICKET_URL, accessToken);

            JsonNode object = restTemplate.getForObject(url, JsonNode.class);
            if (object.has(ConfigConst.TICKET_STRING)) {
                String ticket = object.get(ConfigConst.TICKET_STRING).asText();
                JsApiTicketDTO newJsApiTicketDTO = new JsApiTicketDTO(ticket, getExpireTime());
                writeJsApiTicketToFile(newJsApiTicketDTO, ConfigConst.JS_API_TICKET_CONFIG_JSON);
                return newJsApiTicketDTO.getTicket();
            } else {
                throw new SignException("获取 access_token 失败，错误码：" + object.get("errcode") + "错误信息：" + object.get("errmsg"));
            }
        } else {
            return jsApiTicketDTO.getTicket();
        }
    }

    private JsApiTicketDTO readJsApiTicketFromFile(String fileName) throws IOException {
        JsApiTicketDTO jsApiTicketDTO;
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream is = null;
        try {
            // 添加数据流缓存
            is = new BufferedInputStream(new FileInputStream(new File(fileName)));
            LOGGER.info("将 json 文件中的信息读入 JsApiTicketDTO 对象 ...");
            jsApiTicketDTO = objectMapper.readValue(is, JsApiTicketDTO.class);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return jsApiTicketDTO;
    }

    private void writeJsApiTicketToFile(JsApiTicketDTO jsApiTicketDTO, String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        OutputStream os = null;
        try {
            // 添加数据流缓存
            os = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
            LOGGER.info("将 JsApiTicketDTO 对象中的信息写入 json 文件 ...");
            objectMapper.writeValue(os, jsApiTicketDTO);
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    private String getAccessToken() throws IOException, SignException {
        // access_token 应该全局存储与更新，以下代码以写入到文件中做示例
        AccessTokenDTO accessTokenDTO = readAccessTokenFromFile(ConfigConst.ACCESS_TOKEN_CONFIG_JSON);
        if (accessTokenDTO.getExpireTime() < getCurrentTime()) {
            // 如果是企业号用以下 URL 获取 access_token
            // String url = ConfigConst.QY_ACCESS_TOKEN_URL;
            String url = MessageFormat.format(ConfigConst.ACCESS_TOKEN_URL, configInjection.getAppId(), configInjection.getAppSecret());
            JsonNode object = restTemplate.getForObject(url, JsonNode.class);
            if (object.has(ConfigConst.ACCESS_TOKEN_STRING)) {
                String access_token = object.get(ConfigConst.ACCESS_TOKEN_STRING).asText();
                AccessTokenDTO newAccessTokenDTO = new AccessTokenDTO(access_token, getExpireTime());
                writeAccessTokenToFile(newAccessTokenDTO, ConfigConst.ACCESS_TOKEN_CONFIG_JSON);
                return newAccessTokenDTO.getAccessToken();
            } else {
                throw new SignException("获取 access_token 失败，错误码：" + object.get("errcode") + "错误信息：" + object.get("errmsg"));
            }
        } else {
            return accessTokenDTO.getAccessToken();
        }
    }

    private AccessTokenDTO readAccessTokenFromFile(String fileName) throws IOException {
        AccessTokenDTO accessTokenDTO;
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream is = null;
        try {
            // 添加数据流缓存
            is = new BufferedInputStream(new FileInputStream(new File(fileName)));
            LOGGER.info("将 json 文件中的信息读入 AccessTokenDTO 对象 ...");
            accessTokenDTO = objectMapper.readValue(is, AccessTokenDTO.class);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return accessTokenDTO;
    }

    private void writeAccessTokenToFile(AccessTokenDTO accessTokenDTO, String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        OutputStream os = null;
        try {
            // 添加数据流缓存
            os = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
            LOGGER.info("将 AccessTokenDTO 对象中的信息写入 json 文件 ...");
            objectMapper.writeValue(os, accessTokenDTO);
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}

