package com.example.service;

import com.example.entity.ResponseResult;

public interface Arroundimgservice {
    ResponseResult insertimgmes(String mes,String[] url);
    void insertimgurl(String url);
    void insertmes(String mes);
    ResponseResult selectimg();
    ResponseResult selectimdbyid(int id);
}
