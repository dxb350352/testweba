package com.yihaomen.mybatis.enums;

/**
 *   
 *  @ProjectName: springmvc-mybatis 
 *  @Description:
 *  @author: lisen
 *  @date: 2017/10/23  
 */
public enum Subject {
    CHINESE(0, "语文"), ENGLISH(1, "语文"), MATHEMATICS(2, "数学");
    private int Code;
    private String name;

    Subject(int code, String name) {
        Code = code;
        this.name = name;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Subject getSubject(int code) {
        for(Subject subject : Subject.values()) {
            if(code == subject.getCode()) {
                return subject;
            }
        }
        return null;
    }
}
