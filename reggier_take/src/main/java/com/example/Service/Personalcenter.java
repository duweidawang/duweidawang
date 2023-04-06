package com.example.Service;

import com.example.Entity.ResponseResult;
import com.example.Entity.User;

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
