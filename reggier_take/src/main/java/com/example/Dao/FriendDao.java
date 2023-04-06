package com.example.Dao;

import com.example.Entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface FriendDao {
    List<User> selectfriend(int id);
}
