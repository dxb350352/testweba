package com.yihaomen.service.user;

import com.yihaomen.mybatis.dao.UserMapper;
import com.yihaomen.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;

/**
 *   
 *  @ProjectName: mybatis-learn 
 *  @Description:
 *  @date: 2017/2/11  
 */
public class TestAddUser {
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

    public static void main(String[] args) {
        addUser();

    }

    public static void addUser() {
        User user = new User();
        user.setUserName("spring");
        user.setUserAge("101");
        user.setUserAddress("hangzhou,xihu");
        user.setRegTime(new Date());
        SqlSession session = sqlSessionFactory.openSession();
        try {

            UserMapper userMapper = session.getMapper(UserMapper.class);
            userMapper.addUser(user);
            session.commit();
            System.out.println("当前增加的用户 id为:" + user.getId());
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }
}
