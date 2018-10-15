package com.yihaomen.mybatis.model;

import com.yihaomen.mybatis.enums.Gender;
import com.yihaomen.mybatis.enums.Hobby;
import com.yihaomen.mybatis.enums.Member;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable{

    private static final long serialVersionUID = -8771602622322317151L;
    private int id;
    private String userName;
    private String userAge;
    private Gender gender;
    private Hobby hobby;
    private Member member;
    private String userAddress;
    private Date regTime;
    private List<Article> articleList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
