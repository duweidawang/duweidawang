package com.example.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class jwtutils {
    private static final String signature="ILoveYuzuruHannu";
    /***
     * 生成token header.payload.signature
     */
    public static String getToken(Map<String,String> map){

        Calendar instance=Calendar.getInstance();
        instance.add(Calendar.DATE,7); //设置过期时间7天

        //创建jwt builder
        JWTCreator.Builder builder=JWT.create();

        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())//指定令牌的过期时间
                .sign(Algorithm.HMAC256(signature));//签发算法

        return token;
    }

    /***
     * 验证token
     */
    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(signature)).build().verify(token);
    }

    /***
     * 获取token信息
     * 这个方法可用可不用
     */
    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify=JWT.require(Algorithm.HMAC256(signature)).build().verify(token);

        return verify;
    }
}


