/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.dto.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jm1138 on 2017/10/9.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private String ticket;

    @JsonProperty("expire_time")
    private long expireTime;

}
