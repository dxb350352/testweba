package com.dxb.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by sang on 2017/1/10.
 */
@Configuration
//如果是继承WebMvcConfigurationSupport，会让静态资源无法访问-->>https://www.cnblogs.com/deng720/p/8989388.html
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //不用加个controller方法了
        registry.addViewController("/login").setViewName("login");
    }
}
