package com.example.Controller;

import com.example.Entity.ResponseResult;
import com.example.Entity.User;
import com.example.Service.Userservice;
import com.example.Utils.VerifyCodeUtils;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class Usercontroller {
    @Autowired
    private Userservice userservice;

    /**
     * 登录模块
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody Map<String,String> user,HttpServletRequest request){
        //接受数据
//        String numberimg=user.get("number");
        String account=user.get("account");
        String password=user.get("password");


        User user1=new User();  //创建user对象
        user1.setAccount(account);
        user1.setPassword(password);

        //取出在servletContext作用域中的验证码
        Object code = request.getServletContext().getAttribute("code");


        Map<String,Object> map=new HashMap<>();
        return userservice.loginservice(user1);  //进入service判断是否正确

//        if(numberimg.equals(code)) {
//            return userservice.loginservice(user1);  //进入service判断是否正确
//
//        }
//        else{
//            ResponseResult responseResult=new ResponseResult(401,"miscode");
//
//            return responseResult;
//
//
//        }

    }


    /**
     * 验证码模块
     * @description 生成验证码图片
     * <br>
     * @param request   HttpServletRequest对象
     *                  代表客户端的请求，
     *                  当客户端通过HTTP协议访问服务器时，
     *                  HTTP请求头中的所有信息都封装在这个对象中，
     *                  通过这个对象提供的方法，可以获得客户端请求的所有信息。
     * @return java.lang.String
     * @author 淡
     * @since 2021/2/23 12:47
     */
    @GetMapping("/getImage")
    public String getImageCode(HttpServletRequest request) throws IOException {
        //1.使用工具类生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        System.out.println(code);                      //打印查看

        //2.将验证码放入servletContext作用域
        request.getServletContext().setAttribute("code",code);
        //3.将图片转换成base64
        //字节数组输出流在内存中创建一个字节数组缓冲区，所有发送到输出流的数据保存在该字节数组缓冲区中。
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //将得到的验证码，使用工具类生成验证码图片，并放入到字节数组缓存区
        VerifyCodeUtils.outputImage(220,60,byteArrayOutputStream,code);
        //使用spring提供的工具类，将字节缓存数组中的验证码图片流转换成Base64的形式
        //并返回给浏览器
        return "data:image/png;base64," + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());

    }

    /**
     * 注册功能，返回信息
     * @param
     * @return
     */
    @PostMapping(value = "/register")
    public Map<String,String> register(@RequestBody Map<String, String> user, HttpServletRequest request){
        //接受数据
        String numberimg=user.get("number");
        String account=user.get("account");
        String password=user.get("password");
        String email=user.get("email");

        User user1=new User();  //创建user对象
        user1.setAccount(account);
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encode=encoder.encode(password);
        user1.setPassword(encode);
        user1.setEmail(email);

        //取出在servletContext作用域中的验证码
        Object code = request.getServletContext().getAttribute("code");

        Map<String,String> map1=new HashMap<>();
        if(userservice.checkaccount(account)==1) {  //判断账户是否已存在
            if (userservice.checkemail(email)==1) { //判断邮箱是否已存在

                if (numberimg.equals(code)) {
                    if (userservice.insertaccount(user1).equals("1")) {
                        map1.put("state", "success");     //插入成功

                    } else {
                        map1.put("state", "fail");       //失败
                    }
                } else {
                    map1.put("state", "misimgnumber");  //验证码失败

                }

            }else {
                map1.put("state","exitemail");

            }
        }else {
            map1.put("state","exitaccount");

        }

        return map1;
    }





    @PreAuthorize("hasAuthority('sys_delete')")
    @GetMapping(value = "/log")
    public String log(){
        return "hello";
    }
    @GetMapping(value = "/loginout")
    public ResponseResult logout(){
        return userservice.logout();
    }
    @GetMapping(value = "/getimg")
    public ResponseResult getimg(){
        return userservice.getimg();
    }
    @GetMapping(value = "/demo1")
    public String demo(){
        return "keyil";
    }




}
