/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.exception;

import com.kyyee.wxjssdksign.constant.ErrorCodeEnum;
import com.kyyee.wxjssdksign.dto.StandardResponseDTO;
import com.kyyee.wxjssdksign.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jm1138 on 2017/9/21.
 * aop异常与日志处理类
 */
@ControllerAdvice
@Component
public class ExceptionHandle implements ThrowsAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(SignException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public StandardResponseDTO signException(SignException e) {
        LOGGER.error(e.getMessage());
        return ResponseUtil.error(ErrorCodeEnum.SIGN_EXCEPTION);
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public StandardResponseDTO noSuchAlgorithmException(NoSuchAlgorithmException e) {
        LOGGER.error(e.getMessage());
        return ResponseUtil.error(ErrorCodeEnum.NO_SUCH_ALGORITHM_EXCEPTION);
    }

    @ExceptionHandler(UnsupportedEncodingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public StandardResponseDTO unsupportedEncodingException(UnsupportedEncodingException e) {
        LOGGER.error(e.getMessage());
        return ResponseUtil.error(ErrorCodeEnum.UNSUPPORTED_ENCODING_EXCEPTION);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public StandardResponseDTO ioException(IOException e) {
        LOGGER.error(e.getMessage());
        return ResponseUtil.error(ErrorCodeEnum.IO_EXCEPTION);
    }

    @ExceptionHandler(RestClientException.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    @ResponseBody
    public StandardResponseDTO restClientException(RestClientException e) {
        LOGGER.error(e.getMessage());
        return ResponseUtil.error(ErrorCodeEnum.REST_CLIENT_EXCEPTION);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public StandardResponseDTO nullPointerException(NullPointerException e) {
        LOGGER.error(e.getMessage());
        return ResponseUtil.error(ErrorCodeEnum.NULL_POINTER_EXCEPTION);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public StandardResponseDTO exception(Exception e) {
        LOGGER.error("{}, message: {}", e.getClass(), e.getMessage());
        return ResponseUtil.error(ErrorCodeEnum.UNKNOWN_EXCEPTION);
    }
}
