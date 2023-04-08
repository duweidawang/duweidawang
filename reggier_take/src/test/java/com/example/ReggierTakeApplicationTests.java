package com.example;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.Config.SecurityConfig;
import com.example.Dao.ArroundimgDao;
import com.example.Dao.BokeDao;
import com.example.Dao.Userdao;
import com.example.Entity.ResponseResult;
import com.example.Entity.User;
import com.example.Entity.faceimg;
import com.example.Service.Arroundimgservice;
import com.example.Service.Bokeservice;
import com.example.Service.Userservice;
import com.example.Service.impl.ArroundimgserviceImpl;
import com.example.Utils.jwtutils;
import com.sun.javafx.collections.ListListenerHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ReggierTakeApplicationTests {
    @Autowired
    private Userdao userdao;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    ArroundimgDao arroundimgDao;
    @Autowired
    BokeDao bokeDao;
    @Autowired
    Bokeservice bokeservice;
    @Autowired
    Arroundimgservice arroundimgservice;
    @Autowired
    Userservice userservice;

    @Test
    void contextLoads() {
        User user=new User();
        user.setAccount("202105624");
        user.setPassword("123");
        user.setEmail("11");
        System.out.println(userdao.insertaccount(user));
    }
    @Test
    void testaccount(){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encode=encoder.encode("123");
        System.out.println(encode);


    }
    @Test
    void testaccount1(){
        Map<String,String> map=new HashMap<>();
        map.put("userid","2");
        String token=jwtutils.getToken(map);
        System.out.println(token);

        DecodedJWT tokenInfo = jwtutils.getTokenInfo(token);
        String token1 = tokenInfo.getClaim("userid").asString();
        System.out.println(token1);

    }
    @Test
    void insertmes() {
//        String mes =" ssdfklsdjfa";
//        int id=1;
//        arroundimgDao.insertimgurl(mes,id);
//        System.out.println(arroundimgDao.insertmes(mes));
//        System.out.println(arroundimgDao.selectimgid());
//       List<faceimg> list=arroundimgDao.selectimg();
//        System.out.println(list);
//        System.out.println(userdao.selectbyaccount("202105624"));
//        bokeDao.insertimg("ddd",1);
//        Map map =new HashMap();
        System.out.println(userservice.getimg().getData());


    }


}
