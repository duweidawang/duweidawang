package com.example.service;

import com.example.entity.ResponseResult;

import java.util.List;

public interface Arroundimgservice {
    ResponseResult insertimgmes(String text, List list);

    ResponseResult selectimg();
    ResponseResult selectimdbyid(int id);
    ResponseResult deletearround(int id);
}
