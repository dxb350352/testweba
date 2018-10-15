package com.yihaomen.mybatis.model;

import com.yihaomen.mybatis.enums.Gender;

import java.io.Serializable;
import java.util.List;

/**
 *   
 *  @ProjectName: springmvc-mybatis 
 *  @Description:
 *  @date: 2017/10/23  
 */
public class Student implements Serializable{

    private static final long serialVersionUID = 735655488285535299L;
    private String id;
    private String name;
    private int age;
    private Gender gender;
    private List<Teacher> teachers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", teachers=" + teachers +
                '}';
    }
}
