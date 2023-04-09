package com.example.service;


import com.example.entity.ResponseResult;
import com.example.entity.User;

public interface Userservice {

    String insertaccount(User user);
    int checkaccount(String account);
    int checkemail(String email);
    ResponseResult loginservice(User user);

    ResponseResult logout();

    ResponseResult getimg();

}
