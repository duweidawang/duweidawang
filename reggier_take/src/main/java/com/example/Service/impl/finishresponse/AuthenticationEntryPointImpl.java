package com.example.Service.impl.finishresponse;

import com.alibaba.fastjson.JSON;
import com.example.Entity.ResponseResult;
import com.example.Utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());//401 表示没有授权
        ResponseResult result = new ResponseResult(401,"认证失败请重新登录");
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}

