package com.yihaomen.mybatis.model;

import java.util.Date;

public class UserTwoGroupLink {
    private UserTwo userTwo;
    private Group group;
    private Date createTime;

    public UserTwo getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(UserTwo userTwo) {
        this.userTwo = userTwo;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
