package com.example.handler.exception;

import com.example.common.ResultCodeEnum;
import com.example.common.SystemException;
import com.example.entity.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        //打印异常信息
        log.info("出现了异常！ {}",e);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.error(e.getCode(),e.getMsg());
    }


    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e){
        //打印异常信息
        log.info("出现了异常！ {}",e);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.error(ResultCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());
    }
}
