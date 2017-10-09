/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.controller;

import com.kyyee.wxjssdksign.dto.StandardResponseDTO;
import com.kyyee.wxjssdksign.exception.SignException;
import com.kyyee.wxjssdksign.service.SignService;
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
    public StandardResponseDTO sign(@RequestParam String url)
            throws IOException, NoSuchAlgorithmException, SignException {
        LOGGER.info("url:{}", url);
        return new StandardResponseDTO<>(0, signService.sign(url));
    }
}
