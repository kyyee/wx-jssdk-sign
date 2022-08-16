package com.kyyee.wxjssdksign.exception;


import com.kyyee.wxjssdksign.utils.SpringUtils;

public class ErrorCodeDefaultImpl implements IErrorCode {
    //前五位编码   xx xxx
    private static final String codePrefix;

    static {
        codePrefix = SpringUtils.getProperty("unisinsight.error-code-prefix");
    }

    private final String code;
    private final String message;

    public ErrorCodeDefaultImpl(String code, String message) {
        this.code = codePrefix + code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
