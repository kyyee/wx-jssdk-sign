/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.controller;

import com.kyyee.wxjssdksign.dto.StandardResponseDTO;
import com.kyyee.wxjssdksign.exception.SignException;
import com.kyyee.wxjssdksign.service.SignService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jm1138 on 2017/9/6.
 */
@RestController
@RequestMapping("/sign")
@CrossOrigin
public class WxJssdkSignCtrl {
    private static final Logger LOGGER = LoggerFactory.getLogger(WxJssdkSignCtrl.class);

    @Resource
    private
    SignService signService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "签名接口", notes = "签名接口详细描述")
    public StandardResponseDTO sign(
            @ApiParam(required = true, name = "url", value = "需要签名的url")
            @RequestParam String url)
            throws IOException, NoSuchAlgorithmException, SignException {
        LOGGER.info("url:{}", url);
        return new StandardResponseDTO<>(0, signService.sign(url));
    }
}
