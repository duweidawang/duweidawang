package com.example.dao;

import com.example.entity.User;
import com.example.entity.selectboke;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BokeDao {
    boolean insertimg(String url,int id,int urlid);
    boolean insertboke(int id,String time,String content,int urlid);
    Integer selectmax(int id);

    List<User> selectheadurl(Integer id);
   List<selectboke> selectboke();
   List<String> selecturl(Integer userid, Integer urlid);

  boolean  updataheadimg(String url ,int id);
  boolean insertbokecontent(selectboke selectboke,int userid2);
  List<selectboke> selectbokecontent(selectboke selectboke);
  List<selectboke> selectbokebyid(int id);




}
