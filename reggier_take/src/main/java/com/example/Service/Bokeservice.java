package com.example.Service;

import com.alibaba.druid.sql.dialect.blink.parser.BlinkStatementParser;
import com.example.Entity.ResponseResult;
import com.example.Entity.selectboke;

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
