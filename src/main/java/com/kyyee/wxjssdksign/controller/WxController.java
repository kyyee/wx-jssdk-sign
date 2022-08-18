/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.controller;

import com.kyyee.wxjssdksign.dto.response.SignResDto;
import com.kyyee.wxjssdksign.exception.BaseException;
import com.kyyee.wxjssdksign.service.SignService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @author jm1138
 * @date 2017/9/6
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/kyyee/v1/wx/sign")
@CrossOrigin
@Slf4j
public class WxController {

    @Resource
    private
    SignService signService;

    @GetMapping
    @ApiOperation(value = "签名接口", notes = "签名接口详细描述")
    public SignResDto sign(
        @ApiParam(required = true, name = "url", value = "需要签名的url")
        @RequestParam String url)
        throws IOException, NoSuchAlgorithmException, BaseException {
        return signService.sign(url);
    }
}
