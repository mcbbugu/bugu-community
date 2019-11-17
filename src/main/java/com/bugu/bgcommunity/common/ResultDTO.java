package com.bugu.bgcommunity.common;

import com.fasterxml.jackson.annotation.JsonInclude;
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
//为空不显示
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultDTO <T> {
    Integer code;
    String msg;
    T data;

    public ResultDTO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultDTO(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static <T> ResultDTO ok(T data){
        return new ResultDTO(200, "成功", data);
    }

    public static ResultDTO error(String msg) {
        return new ResultDTO(0, msg);
    }
}