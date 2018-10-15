package com.yihaomen.mybatis.dao;

import com.yihaomen.mybatis.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *   
 *  @ProjectName: mybatis-learn 
 *  @Description: <p>
 * </p>
 *  @date: 2017/2/11  
 */
@Repository("userMapper")
public interface UserMapper {
    public User selectUserByID(int id);
    public List<User> selectUserById2(Long id);
    List<User> getUserArticlesById(Long id);
    public List<User> selectUsers(String userName);
    public void addUser(User user);
    public int addUser2(User user);
    public void updateUser(User user);
    public void deleteById(int id);
}
