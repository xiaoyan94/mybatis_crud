package com.xxx.domain;

/**
 * 可以有多个对象作为QueryVo的属性，一起组成查询条件
 */
public class QueryVo {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
