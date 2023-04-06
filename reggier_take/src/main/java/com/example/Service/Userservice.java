package com.example.Service;


import com.example.Entity.ResponseResult;
import com.example.Entity.User;

public interface Userservice {

    String insertaccount(User user);
    int checkaccount(String account);
    int checkemail(String email);
    ResponseResult loginservice(User user);

    ResponseResult logout();

    ResponseResult getimg();

}
