package com.example.Service;

import com.example.Entity.ResponseResult;

import java.util.List;

public interface Arroundimgservice {
    ResponseResult insertimgmes(String text,List list);

    ResponseResult selectimg();
    ResponseResult selectimdbyid(int id);
    ResponseResult deletearround(int id);
}
