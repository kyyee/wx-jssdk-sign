/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyyee.wxjssdksign.constant.WxProperties;
import com.kyyee.wxjssdksign.dto.bean.AccessToken;
import com.kyyee.wxjssdksign.dto.bean.Ticket;
import com.kyyee.wxjssdksign.dto.response.SignResDto;
import com.kyyee.wxjssdksign.exception.BaseException;
import com.kyyee.wxjssdksign.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Formatter;
import java.util.UUID;

/**
 * Created by jm1138 on 2017/9/7.
 */
@Component
@Slf4j
public class SignServiceImpl implements SignService {
    // 获取access_token的url
    public static final String QY_ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={0}&corpsecret={1}";
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
    // 获取ticket的url
    public static final String QY_TICKET_URL = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=";
    public static final String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={0}&type=jsapi";
    // AccessToken 缓存json文件
    public static final String ACCESS_TOKEN_CONFIG_JSON = "json/access_token.json";
    // Ticket 缓存json文件
    public static final String TICKET_CONFIG_JSON = "json/ticket.json";

    @Resource
    private
    RestTemplate restTemplate;

    @Resource
    private
    WxProperties wxProperties;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public SignResDto sign(String url) throws NoSuchAlgorithmException, IOException, BaseException {
        String jsApiTicket = getTicket();
        String nonceStr = createNonceStr();
        String timestamp = createTimestamp();
        String rawSignature;
        String signature;

        // 注意这里参数名必须全部小写，且参数的顺序要按照 key 值 ASCII 码升序排序
        rawSignature = MessageFormat.format("jsapi_ticket={0}&noncestr={1}&timestamp={2}&url={3}", jsApiTicket, nonceStr, timestamp, url);
        log.info(rawSignature);

        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(rawSignature.getBytes(StandardCharsets.UTF_8));
        signature = byteToHex(crypt.digest());

        return SignResDto.builder()
                .appId(wxProperties.getAppId())
                .nonceStr(nonceStr)
                .timestamp(timestamp)
                .url(url)
                .rawSignature(rawSignature)
                .signature(signature)
                .build();
    }

    private static String byteToHex(final byte[] hash) {
        try (Formatter formatter = new Formatter()) {
            for (byte b : hash) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        }
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


    private String getTicket() throws IOException, BaseException {
        // ticket 应该全局存储与更新，以下代码以写入到文件中做示例，可写入数据库
        Ticket ticket = readTicket();
        if (ticket.getExpireTime() < getCurrentTime()) {
            String accessToken = getAccessToken();
            // 如果是企业号用以下 URL 获取 ticket
            String url = MessageFormat.format(TICKET_URL, accessToken);
            JsonNode object = restTemplate.getForObject(url, JsonNode.class);
            if (object.has("ticket")) {
                String ticketStr = object.get("ticket").asText();
                Ticket newTicket = Ticket.builder()
                        .ticket(ticketStr)
                        .expireTime(getExpireTime())
                        .build();
                writeTicket(newTicket);
                return newTicket.getTicket();
            } else {
                throw new BaseException("获取 access_token 失败，错误码：" + object.get("errcode") + "错误信息：" + object.get("errmsg"));
            }
        } else {
            return ticket.getTicket();
        }
    }

    private Ticket readTicket() throws IOException {
        Ticket ticket;
        try (InputStream is = new BufferedInputStream(new ClassPathResource(TICKET_CONFIG_JSON).getInputStream())) {
            // 添加数据流缓存
            log.info("将 json 文件中的信息读入 Ticket 对象 ...");
            ticket = objectMapper.readValue(is, Ticket.class);
        }
        return ticket;
    }

    private void writeTicket(Ticket ticket) throws IOException {
        try (OutputStream os = new BufferedOutputStream(Files.newOutputStream(new ClassPathResource(TICKET_CONFIG_JSON).getFile().toPath()))) {
            // 添加数据流缓存
            log.info("将 Ticket 对象中的信息写入 json 文件 ...");
            objectMapper.writeValue(os, ticket);
        }
    }

    private String getAccessToken() throws IOException, BaseException {
        // access_token 应该全局存储与更新，以下代码以写入到文件中做示例
        AccessToken accessToken = readAccessToken();
        if (accessToken.getExpireTime() < getCurrentTime()) {
            // 如果是企业号用以下 URL 获取 access_token
            String url = MessageFormat.format(ACCESS_TOKEN_URL, wxProperties.getAppId(), wxProperties.getAppSecret());
            JsonNode object = restTemplate.getForObject(url, JsonNode.class);
            if (object.has("access_token")) {
                String accessTokenStr = object.get("access_token").asText();
                AccessToken newAccessToken = AccessToken.builder()
                        .accessToken(accessTokenStr)
                        .expireTime(getExpireTime()).build();
                writeAccessToken(newAccessToken);
                return newAccessToken.getAccessToken();
            } else {
                throw new BaseException("获取 access_token 失败，错误码：" + object.get("errcode") + "错误信息：" + object.get("errmsg"));
            }
        } else {
            return accessToken.getAccessToken();
        }
    }

    private AccessToken readAccessToken() throws IOException {
        AccessToken accessToken;
        try (InputStream is = new BufferedInputStream(new ClassPathResource(ACCESS_TOKEN_CONFIG_JSON).getInputStream())) {
            // 添加数据流缓存
            log.info("将 json 文件中的信息读入 AccessToken 对象 ...");
            accessToken = objectMapper.readValue(is, AccessToken.class);
        }
        return accessToken;
    }

    private void writeAccessToken(AccessToken accessToken) throws IOException {
        try (OutputStream os = new BufferedOutputStream(Files.newOutputStream(new ClassPathResource(ACCESS_TOKEN_CONFIG_JSON).getFile().toPath()))) {
            // 添加数据流缓存
            log.info("将 AccessToken 对象中的信息写入 json 文件 ...");
            objectMapper.writeValue(os, accessToken);
        }
    }
}

