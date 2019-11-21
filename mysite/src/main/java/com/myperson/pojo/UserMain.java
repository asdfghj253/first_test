package com.myperson.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/9/19 0019.
 */
public class UserMain implements Serializable {
    private Integer userID;
    private String userName;
    private String passWord;
    private Integer userEnable=1;
    private String name;

    public UserMain() {
    }

    public UserMain(Integer userID, String userName, String passWord, Integer userEnable, String name) {
        this.userID = userID;
        this.userName = userName;
        this.passWord = passWord;
        this.userEnable = userEnable;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getUserEnable() {
        return userEnable;
    }

    public void setUserEnable(Integer userEnable) {
        this.userEnable = userEnable;
    }

    @Override
    public String toString() {
        return "UserMain{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", userEnable=" + userEnable +
                ", name='" + name + '\'' +
                '}';
    }
}
