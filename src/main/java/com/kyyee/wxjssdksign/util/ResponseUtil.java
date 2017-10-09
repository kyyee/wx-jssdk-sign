/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.util;

import com.kyyee.wxjssdksign.constant.ErrorCodeEnum;
import com.kyyee.wxjssdksign.dto.StandardResponseDTO;
import org.springframework.http.HttpStatus;

public class ResponseUtil {

    /**
     * 成功时返回
     *
     * @param result 执行成功标志
     * @param data   消息body
     * @return 消息结构体
     */
    public static StandardResponseDTO success(int result, Object data) {
        return new StandardResponseDTO<>(result, data);
    }

    /**
     * 成功时返回
     *
     * @param result 执行成功标志
     * @return 消息结构体
     */
    public static StandardResponseDTO success(int result) {
        return success(result, null);
    }

    /**
     * 成功时返回
     *
     * @param data 消息body
     * @return 消息结构体
     */
    public static StandardResponseDTO success(Object data) {
        return success(0, data);
    }

    /**
     * 成功时无消息body返回
     *
     * @return 消息结构体
     */
    public static StandardResponseDTO success() {
        return success(null);
    }

    /**
     * 失败时返回
     *
     * @param errorCode 错误码
     * @return 消息结构体
     */
    public static StandardResponseDTO error(ErrorCodeEnum errorCode) {
        return new StandardResponseDTO<>(errorCode.code(), errorCode.getMsg(), null);
    }

    /**
     * 失败时返回
     *
     * @param statusCode Http状态码
     * @return 消息结构体
     */
    public static StandardResponseDTO error(HttpStatus statusCode) {
        return new StandardResponseDTO<>(-1 * statusCode.value(), statusCode.getReasonPhrase(), null);
    }
}
