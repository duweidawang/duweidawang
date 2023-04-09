package com.example.filter;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.entity.LoginUser;
import com.example.utils.RedisCache;
import com.example.utils.jwtutils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {


        //我们先拿到请求头中的token
        String token = request.getHeader("token");

        if(StringUtils.isBlank(token)){
            //说明没有携带token 那么直接放行 之后的过滤器肯定会报错，那么就说明用户没有登录
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        String userid;
        try {
            DecodedJWT tokenInfo = jwtutils.getTokenInfo(token);
            userid=tokenInfo.getClaim("id").asString();

        } catch (Exception e) {
            e.printStackTrace();
            //就说明token失效了 或者是token无效
            throw new RuntimeException("token无效");
        }
        //从redis中拿到用户的信息，给SecurityContextHolder设置上下文


       String redisKey="login:"+userid;

       Object object = redisCache.getCacheObject(redisKey);

        LoginUser loginUser=new LoginUser();

            if(object==null){
                filterChain.doFilter(request,response);        //防止退出登录后这里的object为空报错，做个判断直接放行
                return;
            }
            loginUser = JSONObject.parseObject(object.toString(), LoginUser.class);










        if(Objects.isNull(loginUser)){
           throw new RuntimeException("用户未登录");
        }
        //存入SecurityContextHolder上下文当中  注意 这里必须得使用三个参数的authentication
        //第三个参数为授权 也就是用户是啥身份 先不管
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //放行
        filterChain.doFilter(request,response); //那么就正常的请求接口去啦！！！
    }
}
