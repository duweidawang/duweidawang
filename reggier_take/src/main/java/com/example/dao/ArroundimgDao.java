package com.example.dao;

import com.example.entity.faceimg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArroundimgDao {
    int insertmes(faceimg faceimg);
    int selectimgid();
    boolean insertimgurl(String url,int id);
    List<faceimg> selectimg();
    List<faceimg> selectimgbyid(int id);
    boolean deletearround(int id);
    boolean deletearrround1(int id);



}
