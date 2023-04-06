package com.example.Service.impl;

import com.example.Dao.FriendDao;
import com.example.Entity.LoginUser;
import com.example.Entity.ResponseResult;
import com.example.Entity.User;
import com.example.Service.Friendservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FrinedServiceImpl implements Friendservice {
    @Autowired
    FriendDao friendDao;


    @Override
    public ResponseResult selectfriend() {

        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId  = loginUser.getUser().getId();


        List<User> selectfriend = friendDao.selectfriend((int) userId);

        return new ResponseResult(201,"成功",selectfriend);


    }
}
