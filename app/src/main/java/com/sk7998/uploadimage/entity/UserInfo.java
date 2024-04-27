//UserInfo.java
package com.sk7998.uploadimage.entity;

public class UserInfo {

    private int id;
    private String name;
    private String pwd;
    private String phone;
    private int status;


    public UserInfo() {
    }

    public UserInfo(int id, String name, String pwd, String phone, int status) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.phone = phone;
        this.status = status;
    }

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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String password) {
        this.pwd = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

