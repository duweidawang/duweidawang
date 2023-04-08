package com.example.Service.impl;

import com.example.Dao.Userdao;
import com.example.Entity.LoginUser;
import com.example.Entity.ResponseResult;
import com.example.Entity.User;
import com.example.Service.Userservice;
import com.example.Utils.RedisCache;
import com.example.Utils.jwtutils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements Userservice {
    @Autowired
    private Userdao userdao;



    @Override
    public String insertaccount(User user) {
      if(userdao.insertaccount(user)==1){
          return "1";

      }else{
          return "0";
      }

    }

    @Override
    public int checkaccount(String account) {
        if(userdao.checkaccount(account)!=0){
            return 0;
        }
        else{
            return 1;
        }
    }

    @Override
    public int checkemail(String email) {
        if(userdao.checkemail(email)!=0){
            return 0;
        }
        return 1;
    }


    /**
     * 登录服务  校验账号与密码 生成token  将id存入redis
     */
    @Autowired
    private AuthenticationManager authenticationManager;   //

    @Autowired
    private RedisCache redisCache;  //redis工具类

    @Override
    public ResponseResult loginservice(User user) {
        //使用Authentication的实现类
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getAccount(), user.getPassword());

        //手动调用方法去认证 他会自动调用UserDetailsService查 然后对比啥的
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if(Objects.isNull(authenticate)){ //说明输入错误
            throw new  RuntimeException("用户名或密码错误");
        }
        //拿到用户信息 然后生成jwt返回给前端，并且将用户的信息存入redis
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal(); // 这个其实就是UserDetails 也就是LoginUser
        String userId = loginUser.getUser().getId().toString();
        Map<String,String> jwtmap=new HashMap<>();
        jwtmap.put("id",userId);
        String token = jwtutils.getToken(jwtmap);


        redisCache.setCacheObject("login:"+userId,loginUser);//将用户信息直接存入redis


        Map<String, String> map = new HashMap<>();
        map.put("token",token);
        map.put("authentic", loginUser.getUser().getRoleid());
        return new ResponseResult(200,"登录成功",map);


    }

    /**
     * 登录注销得服务，删除redis中的信息
     * @return
     */
    @Override
    public ResponseResult logout() {
        //因为这个方法 是通过了jwt过滤器执行到这里的 所以SecurityContextHolder上下文是一样的
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //拿到用户id删除redis中的数据
        String userId  = loginUser.getUser().getId().toString();
        redisCache.deleteObject("login:"+userId);
        return new ResponseResult(200,"退出成功");
    }

    @Override
    public ResponseResult getimg() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId  = loginUser.getUser().getId();
       Map map= userdao.gethead1(userId);
       return new ResponseResult(200,"成功",map);


    }




}

