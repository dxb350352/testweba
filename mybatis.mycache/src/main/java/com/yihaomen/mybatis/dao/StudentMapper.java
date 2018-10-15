package com.yihaomen.mybatis.dao;

import com.yihaomen.mybatis.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *   
 *  @ProjectName: springmvc-mybatis 
 *  @Description:
 *  @author: lisen
 *  @date: 2017/10/23  
 */
@Repository
public interface StudentMapper {
    List<Student> selectStudents();
    List<Student> selectAllStudents();
}
