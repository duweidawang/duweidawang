package com.example.Dao;

import com.example.Entity.User;
import com.example.Entity.selectboke;
import org.apache.el.stream.StreamELResolverImpl;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.List;
import java.util.Map;

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
