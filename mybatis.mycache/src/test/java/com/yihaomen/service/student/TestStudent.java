package com.yihaomen.service.student;

import com.yihaomen.mybatis.dao.StudentMapper;
import com.yihaomen.mybatis.model.Student;
import com.yihaomen.mybatis.model.Teacher;
import com.yihaomen.service.BaseTest;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 *   
 *  @ProjectName: springmvc-mybatis 
 *  @Description:
 *  @author: lisen
 *  @date: 2017/10/23  
 */
public class TestStudent extends BaseTest {
    public static void testStuTeachRela() {
        SqlSessionFactory sqlSessionFactory = getSession();
        SqlSession session = sqlSessionFactory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        List<Student> list = mapper.selectStudents();

        for(Student s : list) {
            System.out.println("------------------");
            System.out.println(s.getName() + "," + s.getAge() + "," + s.getGender());
            for(Teacher t : s.getTeachers()) {
                System.out.println(t.getName() + "," + t.getGender() + "," + t.getSubject());
            }
        }
    }

    public static void selectAllStudent() {
        SqlSessionFactory sqlSessionFactory = getSession();
        SqlSession session = sqlSessionFactory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        List<Student> list = mapper.selectAllStudents();
        System.out.println(list);
        System.out.println("第二次执行");
        List<Student> list2 = mapper.selectAllStudents();
        System.out.println(list2);
        session.commit();
        System.out.println("二级缓存观测点");
        SqlSession session2 = sqlSessionFactory.openSession();
        StudentMapper mapper2 = session2.getMapper(StudentMapper.class);
        List<Student> list3 = mapper2.selectAllStudents();
        System.out.println(list3);
        System.out.println("第二次执行");
        List<Student> list4 = mapper2.selectAllStudents();
        System.out.println(list4);
        session2.commit();

    }

    public static void main(String[] args) {
//        testStuTeachRela();
        selectAllStudent();
    }
}
