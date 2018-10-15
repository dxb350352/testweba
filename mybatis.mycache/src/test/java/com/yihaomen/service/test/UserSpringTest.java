package com.yihaomen.service.test;

import com.yihaomen.mybatis.dao.UserMapper;
import com.yihaomen.mybatis.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 *   
 *  @ProjectName: springmvc-mybatis 
 *  @Description:
 *  @author: lisen
 *  @date: 2017/11/9  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class UserSpringTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectUserById() {
        List<User> users = userMapper.getUserArticlesById(1L);
        System.out.println("................");
        users.forEach(user -> {
            System.out.println(user.getUserAddress());
        });
    }

}
