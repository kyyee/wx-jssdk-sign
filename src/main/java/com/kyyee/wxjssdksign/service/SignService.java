/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.service;

import com.kyyee.wxjssdksign.dto.response.SignResDto;
import com.kyyee.wxjssdksign.exception.BaseException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jm1083 on 2017/6/5.
 */
@Component
public interface SignService {
    SignResDto sign(String url) throws NoSuchAlgorithmException, IOException, BaseException;
}
