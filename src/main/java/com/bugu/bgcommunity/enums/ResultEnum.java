package com.bugu.bgcommunity.enums;

import lombok.Getter;

/**
 * .
 * Created by 马长宝
 * 2019-02-25 00:11
 */
@Getter
public enum ResultEnum {

    fial_login(0, "登录异常"),
    no_question(0, "没找到问题"),

    ok_send(1, "发布成功"),
    no_login(0, "请先登录"),

    not_img(0, "请上传图片"),
    ok_upload(1, "上传成功"),

    ok_update(1, "更新成功"),
    ok_add(1, "添加成功")
    ;

    public static final String param_error = "参数错误: {}";

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
