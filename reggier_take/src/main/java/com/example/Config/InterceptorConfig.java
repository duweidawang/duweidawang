//package com.example.Config;
//
//import com.example.Controller.Interceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//
//    /**
//     * 重写addInterceptors()实现拦截器
//     * 配置：要拦截的路径以及不拦截的路径
//     *
//     * @param registry
//     * @Author 有梦想的肥宅
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //注册Interceptor拦截器(Interceptor这个类是我们自己写的拦截器类)
//        InterceptorRegistration registration = registry.addInterceptor(new Interceptor());
//        //addPathPatterns()方法添加需要拦截的路径
//        registration.addPathPatterns("/**");                      //所有路径都被拦截
//        //excludePathPatterns()方法添加不拦截的路径
//        registration.excludePathPatterns(
//                "/user/getImage",       //添加不拦截路径
//                "/user/login",         //登录页面的地址【不拦截】
//
//                "/user/register",
//                "/**/*.html",            //html静态资源
//                "/**/*.js",              //js静态资源
//                "/**/*.css"              //css静态资源
//        );
//    }
//
//
//}
