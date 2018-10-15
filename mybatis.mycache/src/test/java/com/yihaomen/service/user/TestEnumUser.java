package com.yihaomen.service.user;

import com.yihaomen.mybatis.dao.UserMapper;
import com.yihaomen.mybatis.enums.Gender;
import com.yihaomen.mybatis.enums.Hobby;
import com.yihaomen.mybatis.enums.Member;
import com.yihaomen.mybatis.model.User;
import com.yihaomen.service.BaseTest;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Date;
import java.util.List;

/**
 *   
 *  @ProjectName: springmvc-mybatis 
 *  @Description:
 */
public class TestEnumUser extends BaseTest {
    public static void main(String[] args) {
//        testAddEnum();
        testGetEnumUser();
    }

    /**
     * 添加
     */
    public static void testAddEnum() {
        SqlSession sqlSession = null;
        try {
            SqlSessionFactory sqlSessionFactory = getSession();
            sqlSession = sqlSessionFactory.openSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUserName("小明");
            user.setGender(Gender.MALE);
            user.setHobby(Hobby.FOOTBALL);
            user.setMember(Member.FATHER);
            user.setRegTime(new Date());
            user.setUserAddress("地球");
            user.setUserAge("22");
            int result = mapper.addUser2(user);
            System.out.println(result);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * select
     */
    public static void testGetEnumUser() {
        SqlSession sqlSession = null;
        try {
            SqlSessionFactory sqlSessionFactory = getSession();
            sqlSession = sqlSessionFactory.openSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> users = mapper.selectUserById2(11L);
            for(User u : users) {
                System.out.println(u.getGender());
                System.out.println(u.getHobby());
                System.out.println(u.getMember());
            }
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
