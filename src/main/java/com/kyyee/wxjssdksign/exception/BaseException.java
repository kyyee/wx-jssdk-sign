/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.exception;

/**
 * Created by jm1138 on 2017/9/12.
 * http请求解析异常
 */
public class BaseException extends Exception {
    private static final long serialVersionUID = 3017745546033930196L;

    protected String code;

    public BaseException(String message) {
        super(message);
    }

    public static BaseException of(IErrorCode errorCode) {
        BaseException exception = new BaseException(errorCode.getMessage());
        exception.code = errorCode.getCode();
        return exception;
    }

    public static BaseException of(IErrorCode errorCode, String message) {
        BaseException exception = new BaseException(message);
        exception.code = errorCode.getCode();
        return exception;
    }

    public static BaseException of(BaseErrorCode baseErrorCode) {
        return of(baseErrorCode.of());
    }

    public static BaseException of(BaseErrorCode baseErrorCode, String message) {
        BaseException exception = new BaseException(message);
        exception.code = baseErrorCode.getCode();
        return exception;
    }

    public static BaseException of(IErrorCode errorCode, String message, Object... values) {
        return of(errorCode, replace(message, values));
    }


    public static BaseException of(String errorCode, String message, Object... values) {
        BaseException exception = new BaseException(replace(message, values));
        exception.code = errorCode;
        return exception;
    }

    private static String replace(String message, Object... values) {
        if (values != null) {
            for (Object val : values) {
                if (message.contains("{}")) {
                    message = message.replaceFirst("\\{}", String.valueOf(val));
                }
            }
        }
        return message;
    }

    public String getCode() {
        return code;
    }
}
