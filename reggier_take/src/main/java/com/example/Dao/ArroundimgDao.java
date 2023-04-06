package com.example.Dao;

import com.example.Entity.faceimg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArroundimgDao {
    boolean insertmes(String mes);
    int selectimgid();
    boolean insertimgurl(String url,int id);
    List<faceimg> selectimg();
    List<faceimg> selectimgbyid(int id);




}
