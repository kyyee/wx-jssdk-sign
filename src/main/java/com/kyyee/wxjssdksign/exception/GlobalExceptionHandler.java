/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.exception;

import com.kyyee.wxjssdksign.common.base.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

/**
 * Created by jm1138 on 2017/9/21.
 * aop异常与日志处理类
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler implements ThrowsAdvice {

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> signException(BaseException e) {
        log.error(e.getMessage());
        return ResponseDto.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RestClientException.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public ResponseDto<?> restClientException(RestClientException e) {
        log.error(e.getMessage());
        return ResponseDto.of(BaseErrorCode.CALL_FAILED);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> exception(Exception e) {
        log.error("{}, message: {}", e.getClass(), e.getMessage());
        return ResponseDto.of(BaseErrorCode.SYS_INTERNAL_ERROR);
    }
}
