package com.bugu.bgcommunity.common;

import lombok.Data;
import java.io.Serializable;
/**
 * .
 * Created by mcbbugu
 * 2019-11-07 22:33
 */

@Data
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int NO_LOGIN = -1;

    public static final int SUCCESS = 0;

    public static final int CHECK_FAIL = 1;

    public static final int NO_PERMISSION = 2;

    public static final int UNKNOWN_EXCEPTION = -99;

    private T data;

    private int code = SUCCESS;
    private String msg = "success";

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
        this.code = SUCCESS;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = UNKNOWN_EXCEPTION;
    }
}