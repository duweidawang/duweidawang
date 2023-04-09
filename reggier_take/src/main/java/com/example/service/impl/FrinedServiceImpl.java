package com.example.service.impl;

import com.example.dao.FriendDao;
import com.example.entity.LoginUser;
import com.example.entity.ResponseResult;
import com.example.entity.User;
import com.example.service.Friendservice;
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
