/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jm1138 on 2017/10/9.
 */
public class JsApiTicketDTO {
    private String ticket;

    @JsonProperty("expire_time")
    private long expireTime;

    public JsApiTicketDTO() {
    }

    public JsApiTicketDTO(String ticket, long expireTime) {
        this.ticket = ticket;
        this.expireTime = expireTime;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "JsApiTicketDTO{" +
                "ticket='" + ticket + '\'' +
                ", expireTime='" + expireTime + '\'' +
                '}';
    }
}
