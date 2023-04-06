package com.example.Dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface menudao {
    List<String> selectPermsByUserId(Long id);



}
