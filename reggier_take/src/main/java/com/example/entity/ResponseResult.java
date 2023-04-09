package com.example.entity;


import com.example.common.ResultCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult <T> {
    private Integer code;
    private String msg;
    private T data;

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(ResultCodeEnum resultCodeEnum, T data) {
        this.code = resultCodeEnum.getCode();
        this.data = data;
        this.msg = resultCodeEnum.getMessage();
    }

    public ResponseResult(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMessage();
    }

    public static<T> ResponseResult<T> success(T data) {
        return new ResponseResult<T>(ResultCodeEnum.SUCCESS, data);
    }
    public static<T> ResponseResult<T> success() {
        return new ResponseResult<T>(ResultCodeEnum.SUCCESS);
    }


    public static<T> ResponseResult<T> error(Integer code, String msg) {
        return new ResponseResult<T>(code, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
