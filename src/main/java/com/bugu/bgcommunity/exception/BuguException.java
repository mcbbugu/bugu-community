package com.bugu.bgcommunity.exception;

import com.bugu.bgcommunity.enums.ResultEnum;
import lombok.Data;

/**
 * .
 * Created by 马长宝
 * 2019-02-25 00:10
 */

@Data
public class BuguException extends RuntimeException{

    private Integer code;

    public BuguException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public BuguException(Integer code, String msg){
        super(msg);
    }
}