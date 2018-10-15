package com.yihaomen.service.user;

import com.yihaomen.mybatis.dao.UserMapper;
import com.yihaomen.mybatis.model.Article;
import com.yihaomen.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 *   
 *  @ProjectName: springmvc-mybatis 
 *  @Description:
 */
public class TestGetUser {
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
    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }
    public static void findUserById(Long id){
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> users = userMapper.selectUserById2(id);
        for(User u : users) {
            System.out.println(u.getRegTime());
        }
    }

    public static void getUserArticlesById(Long id) {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> users = userMapper.getUserArticlesById(id);
        for(User u : users) {
            System.out.println("------------------");
            System.out.println(u.getUserName());
            for(Article a : u.getArticleList()) {
                System.out.println(a.getTitle() + "," + a.getContent());
            }
        }
    }

    public static void main(String[] args) {
        findUserById(1l);
//        getUserArticlesById(1L);
    }
}
