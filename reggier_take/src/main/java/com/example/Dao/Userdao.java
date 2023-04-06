package com.example.Dao;

import com.example.Entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Userdao {
    User selectaccount(User user);
    int insertaccount(User user);
    int checkaccount(String account);
    int checkemail(String email);
    User selectbyaccount(String account);
    String gethead(long id);
    User selectuser(long id);
    boolean atention(int id,int userid);
    boolean notattention(int id,int userid);
    int ifattention(int id,int userid);
    boolean updatemessage(User user);



}
