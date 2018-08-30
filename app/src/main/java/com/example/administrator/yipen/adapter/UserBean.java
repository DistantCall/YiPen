package com.example.administrator.yipen.adapter;

public class UserBean {
private String time;
private String  domicile;
private String name;
private String sun;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public UserBean(String time, String domicile, String name, String sun) {
        this.time = time;
        this.domicile = domicile;
        this.name = name;
        this.sun = sun;
    }
}
