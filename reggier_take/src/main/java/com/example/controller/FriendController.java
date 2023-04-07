package com.example.controller;

import com.example.entity.ResponseResult;
import com.example.service.Friendservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    Friendservice friendservice;

    /**
     * 返回好友信息
     * @return
     */
    @PreAuthorize("hasAuthority('sys_cilent')")
    @GetMapping(value = "/myfriend")
    public ResponseResult findfrined(){


        return friendservice.selectfriend();

    }


}
