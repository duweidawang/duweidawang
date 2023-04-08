package com.example.controller;

import com.example.entity.ResponseResult;
import com.example.entity.selectboke;
import com.example.service.Bokeservice;
import com.example.utils.QiniuUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/boke")
public class BokeController {
    @Autowired
    Bokeservice bokeservice;
    @Autowired
    QiniuUtils qiniuUtils;


    /**
     * 动态发表 文本上传接口
     *
     * @param
     * @return
     */
    @PreAuthorize("hasAuthority('sys_cilent')")
    @PostMapping(value = "/uploaded")
    public ResponseResult uploadtext(@RequestParam("text") String text,@RequestParam("time") String time,@RequestParam("file") MultipartFile[] files) {
        List<String> qinimgurl =new ArrayList<>();
        for (MultipartFile file:files) {
            String originlFilename = file.getOriginalFilename();
            String originalFilename = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
            boolean upload = qiniuUtils.upload(file, fileName);
            qinimgurl.add(QiniuUtils.url+fileName);
        }
        return bokeservice.insertboke(text,time,qinimgurl);

    }

    /**
     * 获取boke信息
     *
     * @return
     */
    @PreAuthorize("hasAuthority('sys_cilent')")
    @GetMapping(value = "/getboke")
    public ResponseResult selectboke() {
        return bokeservice.returnboke();
    }

    /**
     * 更改头像
     *
     * @param file
     * @return
     */
    @PreAuthorize("hasAuthority('sys_cilent')")
    @PostMapping(value = "/updatahead")
    public ResponseResult updatahead(@RequestBody MultipartFile file) {

        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        boolean upload = qiniuUtils.upload(file, fileName);
        return bokeservice.updataheadimg(QiniuUtils.url + fileName);

    }

    /**
     * bokecontent的上传
     *
     * @param selectboke
     * @return
     */
    @PreAuthorize("hasAuthority('sys_cilent')")
    @PostMapping(value = "/uploadcontent")
    public ResponseResult uploadcontent(@RequestBody selectboke selectboke) {

        return bokeservice.insertbokecontent(selectboke);


    }

    /**
     * 获得bokecontent
     *
     * @param selectboke
     * @return
     */
    @PreAuthorize("hasAuthority('sys_cilent')")
    @PostMapping(value = "/getbokecontent")
    public ResponseResult getcontent(@RequestBody selectboke selectboke) {

        return bokeservice.getbokecontent(selectboke);

    }
}
