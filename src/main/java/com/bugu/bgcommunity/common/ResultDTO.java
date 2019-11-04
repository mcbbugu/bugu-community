package com.bugu.bgcommunity.common;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * .
 * Created by mcbbugu
 * 2019-10-28 13:15
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultDTO <T> {
    Integer code;
    String msg;
    T data;

    public ResultDTO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultDTO ok(T data){
        ResultDTO dto = new ResultDTO(200, "成功", data);
        return dto;
    }
}