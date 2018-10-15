package com.yihaomen.service.user;

import com.yihaomen.mybatis.dao.UserMapper;
import com.yihaomen.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;


public class TestUpdateUser {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        updateUser();
    }

    public static void updateUser() {
        User user = new User();
        user.setId(3);
        user.setUserAge("20");
        user.setUserName("yingying");
        user.setUserAddress("hangzhou,gongshu");

        SqlSession session = sqlSessionFactory.openSession();
        try{
            UserMapper userMapper = session.getMapper(UserMapper.class);
            userMapper.updateUser(user);
            session.commit();
        } finally {
            session.close();
        }
    }
}
