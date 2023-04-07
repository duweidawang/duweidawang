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
import io.lettuce.core.models.role.RedisReplicaInstance;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

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


    /**
     * 周边的上传接口
     * @param text
     * @param files
     * @return
     */
    @PreAuthorize("hasAuthority('sys_uploadarround')")
    @PostMapping(value = "/upload")
    public ResponseResult uploadarround(@RequestParam("text") String text,@RequestParam("files") MultipartFile[] files){
        List<String> qinimgurl =new ArrayList<>();
        for (MultipartFile file:files) {
            String originlFilename = file.getOriginalFilename();
            String originalFilename = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
            boolean upload = qiniuUtils.upload(file, fileName);
            qinimgurl.add(QiniuUtils.url+fileName);
        }
       return arroundimgservice.insertimgmes(text,qinimgurl);

    }
    /**
     *
     */
    @GetMapping(value="deletearround")
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



