package com.s4y.service4you.Entities;

import java.util.Date;

/**
 * Created by boldi on 2018. 09. 10..
 */

public class User {
    private String UserName;
    private String Email;
    private String Password;
    private String SurName;
    private String MidName;
    private Date Birthday;
    private int Avatar;

    public User(){

    }
    public User (String Username, String Email, String Password, String SurName, String MidName, Date Birthday, int avatar){
        this.UserName = Username;
        this.Email = Email;
        this.Password = Password;
        this.SurName = SurName;
        this.MidName = MidName;
        this.Birthday = Birthday;
        this.Avatar = avatar;
    }
    public User (String Username, String Email, String Password, String SurName, String MidName, int avatar){
        this.UserName = Username;
        this.Email = Email;
        this.Password = Password;
        this.SurName = SurName;
        this.MidName = MidName;
        this.Avatar = avatar;
    }
    public User (String UserName, String Email, String Password, int avatar){
        this.UserName = UserName;
        this.Email = Email;
        this.Password = Password;
        this.Avatar = avatar;
    }

    public int getAvatar() {
        return Avatar;
    }

    public void setAvatar(int avatar) {
        Avatar = avatar;
    }



    public String getUserName() {
        return UserName;
    }
    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public String getMidName() {
        return MidName;
    }

    public void setMidName(String midName) {
        MidName = midName;
    }

}
