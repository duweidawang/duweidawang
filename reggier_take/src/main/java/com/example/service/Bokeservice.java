package com.example.service;


import com.alibaba.druid.sql.dialect.blink.parser.BlinkStatementParser;
import com.example.entity.ResponseResult;
import com.example.entity.selectboke;
import com.example.entity.ResponseResult;
import com.example.entity.selectboke;

import java.util.List;
import java.util.Map;

public interface Bokeservice {
    //插入动态
    ResponseResult insertboke(String text, String time, List list);
    //返回动态信息
    ResponseResult returnboke();
    //更改头像
    ResponseResult updataheadimg(String url);
    ResponseResult insertbokecontent(selectboke selectboke);
    ResponseResult getbokecontent(selectboke selectboke);
}
