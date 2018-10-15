package com.yihaomen.mybatis.model;

import com.yihaomen.mybatis.enums.Gender;
import com.yihaomen.mybatis.enums.Subject;

import java.util.List;

/**
 *   
 *  @ProjectName: springmvc-mybatis 
 *  @Description:
 *  @date: 2017/10/23  
 */
public class Teacher {
    private int id;
    private String name;
    private Gender gender;
    private Subject subject;
    private String degree;
    private List<Student> students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", subject=" + subject +
                ", degree='" + degree + '\'' +
                ", students=" + students +
                '}';
    }
}
