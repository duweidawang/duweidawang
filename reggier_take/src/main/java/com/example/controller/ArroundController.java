package com.example.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.entity.LoginUser;
import com.example.entity.ResponseResult;
import com.example.service.Arroundimgservice;
import com.example.service.Bokeservice;
import com.example.utils.QiniuUtils;
import com.example.utils.jwtutils;
import io.lettuce.core.models.role.RedisReplicaInstance;

import com.example.entity.ResponseResult;
import com.example.service.Arroundimgservice;
import com.example.service.Bokeservice;
import com.example.utils.QiniuUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

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
    /**
     * 周边的上传接口
     * @param
     * @param
     * @return
     */
    @PreAuthorize("hasAuthority('sys_uploadarround')")
    @PostMapping(value = "/upload")
    public ResponseResult uploadarround(@RequestBody Map map){

        return arroundimgservice.insertimgmes((String) map.get("text"),(List) map.get("imgurl"));
    }
    //周边图片上传接口
    @PreAuthorize("hasAuthority('sys_uploadarround')")
    @PostMapping(value = "/uploadimg")
    public ResponseResult uploadimg(@RequestParam("file") MultipartFile file){
        String originlFilename = file.getOriginalFilename();
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        boolean upload = qiniuUtils.upload(file, fileName);
        return new ResponseResult(200,"成功",QiniuUtils.url+fileName);
    }




    /**
     *删除arroind根据id
     */
    @GetMapping(value="deletearround/{id}")
    public ResponseResult deletearround(@PathVariable("id") int id){
        return arroundimgservice.deletearround(id);

    }
    /**
     * 周边内容回显
     * @return
     */
    @PreAuthorize("hasAuthority('sys_cilent')")
    @GetMapping (value = "/text")
    public ResponseResult returntext() {
        return arroundimgservice.selectimg();
    }

    /**
     * 根据id查询周边内容
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('sys_cilent')")
    @GetMapping(value = "/arroundtext/{id}")
    public ResponseResult returnarrtext(@PathVariable int id){
        return arroundimgservice.selectimdbyid(id);
    }
}



