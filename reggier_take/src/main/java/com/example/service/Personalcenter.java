package com.example.service;

import com.example.entity.ResponseResult;
import com.example.entity.User;

public interface Personalcenter {
    //自己查看自己个人
    ResponseResult getuser();
    //别人查看自己
    ResponseResult getotheruser(int id);
    //关注别人
    ResponseResult attention(int id);
    //取消关注
    ResponseResult notattention(int id);
    ResponseResult getotherimg(int id);
    //更改信息
    ResponseResult updatemessage(User user);
    ResponseResult getbokebyid(int id);
}
