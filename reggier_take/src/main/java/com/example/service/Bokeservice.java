package com.example.service;

import com.example.entity.ResponseResult;
import com.example.entity.selectboke;

import java.util.Map;

public interface Bokeservice {
    //插入动态图片
    ResponseResult insertimg(String url);
    //插入动态
    ResponseResult insertboke(Map<String,String> map);
    //返回动态信息
    ResponseResult returnboke();
    //更改头像
    ResponseResult updataheadimg(String url);
    ResponseResult insertbokecontent(selectboke selectboke);
    ResponseResult getbokecontent(selectboke selectboke);
}
