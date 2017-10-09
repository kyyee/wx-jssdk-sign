/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.interceptor;

import com.kyyee.wxjssdksign.constant.ConfigConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // TODO 认证授权处理
        String requestURI = httpServletRequest.getRequestURI();
        LOGGER.info("AuthInterceptor requestURI：{}", requestURI);
        if (ConfigConst.OBLIQUE.equals(requestURI)) {
            LOGGER.error("requestURI is null!!!");
            httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
            httpServletResponse.setHeader(ConfigConst.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE);
            httpServletResponse.getOutputStream()
                    .write(HttpStatus.NOT_FOUND.getReasonPhrase()
                            .getBytes(ConfigConst.CHARSET_UTF8));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
