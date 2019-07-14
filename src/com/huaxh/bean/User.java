package com.huaxh.bean;

public class User {
    private int id;
    private int username; // todo 这里是不是改为String要好一些
    private int password; // todo 这里也应该是String吧

    public User() {
    }

    public User(int username, int password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
