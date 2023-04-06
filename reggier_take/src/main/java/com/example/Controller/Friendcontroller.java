package com.example.Controller;

import com.example.Entity.ResponseResult;
import com.example.Service.Friendservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/friend")
public class Friendcontroller {
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
