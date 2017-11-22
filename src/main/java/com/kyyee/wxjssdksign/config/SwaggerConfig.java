/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author kyyee
 * @date 2017/7/13
 * @since 1.0.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kyyee.wxjssdksign.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 大标题
                .title("微信网页开发，接入微信JS-SDK")
                // 详细描述
                .description("EHR Platform's REST API, all the applications could access the Object model data via JSON.")
                // 版本
                .version("1.0")
                .termsOfServiceUrl("http://kyyee.com")
                // 作者
                .contact(new Contact("kyyee", "http://blog.csdn.net/kyyee", "kyyeeyoung@163.com"))
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }
}
