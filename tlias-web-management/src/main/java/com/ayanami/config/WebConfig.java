package com.ayanami.config;


import com.ayanami.interceptor.DemoInterceptor;
import com.ayanami.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration//底层封装了component
public class WebConfig implements WebMvcConfigurer {
//    @Autowired
//   private DemoInterceptor demoInterceptor;
////    @Autowired
////    private TokenInterceptor tokenInterceptor;
//    //注册拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(demoInterceptor).addPathPatterns("/**");
////        registry.addInterceptor(tokenInterceptor).addPathPatterns("/*")//拦截所有请求
////                .excludePathPatterns("/login");//不拦截哪些请求
////        WebMvcConfigurer.super.addInterceptors(registry);
//    }
}
