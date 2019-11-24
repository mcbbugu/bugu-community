package com.bugu.bgcommunity.enums;

import lombok.Getter;

/**
 * .
 * Created by 马长宝
 * 2019-02-25 00:11
 */
@Getter
public enum ResultEnum {

    fial_login(1, "登录异常"),
    no_question(2, "没找到问题"),

    ok_send(200, "发布成功"),
    no_login(0, "请先登录"),

    not_img(0, "请上传图片"),
    ok_upload(1, "上产成功"),
    ;

    public static final String param_error = "参数错误: {}";

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
