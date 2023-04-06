package com.example.Service;

import com.example.Entity.ResponseResult;

import java.util.List;

public interface Arroundimgservice {
    ResponseResult insertimgmes(String mes,String[] url);
    void insertimgurl(String url);
    void insertmes(String mes);
    ResponseResult selectimg();
    ResponseResult selectimdbyid(int id);
}
