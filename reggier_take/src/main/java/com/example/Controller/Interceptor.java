//package com.example.Controller;
//
//import com.auth0.jwt.exceptions.AlgorithmMismatchException;
//import com.auth0.jwt.exceptions.SignatureVerificationException;
//import com.auth0.jwt.exceptions.TokenExpiredException;
//import com.example.Utils.jwtutils;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.security.SignatureException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Interceptor implements HandlerInterceptor {
//    /**
//     * 在请求处理之前进行调用（Controller方法调用之前）
//     * @Author 有梦想的肥宅
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//
//        if (request.getMethod().equals("OPTIONS")) {
//            response.setStatus(HttpServletResponse.SC_OK);
//            return true;
//        } //不太明白这是神马，但在前端header中有token，但在拦截器中无法获取，加了这个就可以了
//
//        Map<String,Object> map= new HashMap<>();
//        //获取请求头中的令牌
//        String token=request.getHeader("token");
//
//        try {
//            jwtutils.verify(token); //验证令牌
//            return true;      //放行请求
//        }catch (SignatureVerificationException e){
//            e.printStackTrace();
//            map.put("msg","无效签名");
//        }catch (TokenExpiredException e){
//            e.printStackTrace();
//            map.put("msg","token过期");
//        }catch(AlgorithmMismatchException e){
//            e.printStackTrace();
//            map.put("msg","token算法不一致");
//
//        }catch (Exception e){
//            e.printStackTrace();
//            map.put("msg","token无效");
//        }
//        map.put("state",false);//设置状态
//        //将map 转为 json
//        String json =new ObjectMapper().writeValueAsString(map);
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().println(json); //返回信息
//        return false;
//       //如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
//        //如果设置为true时，请求将会继续执行后面的操作
//    }
//    /*
//   controller执行后，页面渲染前
//    */
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//    }
//    /*
//    页面渲染后
//     */
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//
//    }
//}
