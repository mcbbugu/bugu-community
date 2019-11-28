package com.bugu.bgcommunity.common;

import com.bugu.bgcommunity.enums.ResultEnum;
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
    ResultEnum resultEnum;

    public ResultDTO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultDTO(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResultDTO(int code, T data){
        this.code = code;
        this.data = data;
    }

    public ResultDTO(ResultEnum resultEnum){
        this.resultEnum = resultEnum;
    }

    public static <T> ResultDTO ok(T data){
        return new ResultDTO(1, data);
    }

    public static <T> ResultDTO ok(ResultEnum resultEnum){
        return new ResultDTO(resultEnum);
    }

    public static <T> ResultDTO error(ResultEnum resultEnum){
        return new ResultDTO(resultEnum);
    }

    public static ResultDTO error(String msg) {
        return new ResultDTO(0, msg);
    }
}