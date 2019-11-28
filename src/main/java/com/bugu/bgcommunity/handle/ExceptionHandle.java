package com.bugu.bgcommunity.handle;

import com.bugu.bgcommunity.common.ResultDTO;
import com.bugu.bgcommunity.exception.BuguException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * .
 * Created by 马长宝
 * 2019-03-01 16:44
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler
    @ResponseBody
    public ResultDTO handle(Exception e){
        if(e instanceof BuguException){
            BuguException buguException = (BuguException) e;
            return ResultDTO.error(buguException.getMessage());
        }else if(e instanceof NullPointerException){
            log.info("空指针异常，原因：" + e);
            return ResultDTO.error("空指针异常，原因：" + e);
        }else{
            log.error("【系统异常】{}", e);
            return ResultDTO.error("未知错误");
        }
    }
}