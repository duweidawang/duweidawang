package com.example.controller;

import com.example.entity.ResponseResult;
import com.example.entity.User;
import com.example.service.Personalcenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class PersonalcenterController {

    @Autowired
    private Personalcenter personalcenter;

    /**
     * 自己查看自己个人中心
     * @return
     */
    @PreAuthorize("hasAuthority('sys_cilent')")
    @GetMapping(value = "/getuser")
    public ResponseResult getuser(){
        return personalcenter.getuser();

    }

    /**
     * 查看别人个人空间
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('sys_cilent')")
    @PostMapping(value = "/otheruser/{id}")
    public ResponseResult getotheruser(@PathVariable int id){
        return personalcenter.getotheruser(id);

    }

    /**
     * 返回关注成功信息
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('sys_cilent')")
    @PostMapping(value = "/otherattention/{id}")
    public ResponseResult contentation(@PathVariable int id){
       return personalcenter.attention(id);

    }

    /**
     * 取消关注
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('sys_cilent')")
    @PostMapping(value = "/notattention/{id}")
    public ResponseResult notatattion(@PathVariable int id){
        return personalcenter.notattention(id);

    }

    /**
     * 获得id 查看别人个人空间
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('sys_cilent')")
    @GetMapping(value = "/getimg1/{id}")
    public ResponseResult getimg(@PathVariable int id){
        return  personalcenter.getotherimg(id);
    }

    /**
     * 更改个人信息
     * @param user
     * @return
     */
    @PreAuthorize("hasAuthority('sys_cilent')")
    @PostMapping(value ="/updatamessage")
    public ResponseResult updatamessage(@RequestBody User user){
        return personalcenter.updatemessage(user);

    }
    @PostMapping(value = "/getdtai/{id}")
    public ResponseResult getdtai(@PathVariable int id){
        return personalcenter.getbokebyid(id);
    }




}
