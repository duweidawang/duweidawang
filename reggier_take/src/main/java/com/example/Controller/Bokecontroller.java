package com.example.Controller;

import com.example.Entity.ResponseResult;
import com.example.Entity.selectboke;
import com.example.Service.Bokeservice;
import com.example.Utils.QiniuUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/boke")
public class Bokecontroller {
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
    public ResponseResult uploadtext(@RequestBody Map map) {
        return bokeservice.insertboke((String)map.get("text"),(String)map.get("time"),(List) map.get("imgurl"));

    }

    /**
     * 上传时返回图片回显
     * @param file
     * @return
     */
    @PreAuthorize("hasAuthority('sys_cilent')")
    @PostMapping(value = "/uploadimg")
    public ResponseResult uploadtext(@RequestParam("file") MultipartFile file) {
            String originlFilename = file.getOriginalFilename();
            String originalFilename = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
            boolean upload = qiniuUtils.upload(file, fileName);
            return new ResponseResult(200,"成功",QiniuUtils.url+fileName);
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
