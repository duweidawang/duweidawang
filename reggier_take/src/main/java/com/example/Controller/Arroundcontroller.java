package com.example.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.Entity.LoginUser;
import com.example.Entity.ResponseResult;
import com.example.Service.Arroundimgservice;
import com.example.Service.Bokeservice;
import com.example.Utils.QiniuUtils;
import com.example.Utils.jwtutils;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/user")
public class Arroundcontroller {
    @Autowired
    Arroundimgservice arroundimgservice;
    @Autowired
    Bokeservice bokeservice;
    @Autowired
    QiniuUtils qiniuUtils;
    int o = 0;
    @PreAuthorize("hasAuthority('sys_uploadarround')")
    @PostMapping(value = "/upload")
    public ResponseResult arroundcreat(@RequestParam MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        boolean upload = qiniuUtils.upload(file, fileName);
        arroundimgservice.insertimgurl(QiniuUtils.url + fileName);
        return new ResponseResult(1, "", "");

    }
    @PreAuthorize("hasAuthority('sys_uploadarround')")
    @PostMapping(value = "/uploadtext")
    public ResponseResult uploadtext(@RequestBody Map<String,String> map) {
        arroundimgservice.insertmes(map.get("text"));
        return new ResponseResult(1, "新增成功", "");

    }
    @PreAuthorize("hasAuthority('sys_cilent')")
    @GetMapping (value = "/text")
    public ResponseResult returntext() {
        return arroundimgservice.selectimg();
    }



    @PreAuthorize("hasAuthority('sys_cilent')")
    @GetMapping(value = "/arroundtext/{id}")
    public ResponseResult returnarrtext(@PathVariable int id){
        return arroundimgservice.selectimdbyid(id);
    }
}



