package com.bugu.bgcommunity.enums;

import lombok.Getter;

/**
 * .
 * Created by 马长宝
 * 2019-02-25 00:11
 */
@Getter
public enum ResultEnum {

    LOGIN_FIAL(1, "登录异常");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
