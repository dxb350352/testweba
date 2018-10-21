package com.dxb.sso.starter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: WangSaiChao
 * @date: 2018/8/6
 * @description: 配置该注解开启cas功能，用了这种方法就不需要在spring.factories里加了
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(CasClientConfiguration.class)
public @interface EnableCasClient {

}