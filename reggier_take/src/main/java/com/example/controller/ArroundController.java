package com.example.controller;

import com.example.entity.ResponseResult;
import com.example.service.Arroundimgservice;
import com.example.service.Bokeservice;
import com.example.utils.QiniuUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/user")
public class ArroundController {
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



