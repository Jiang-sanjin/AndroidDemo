package com.example.demo_no10_app26;

public class LocalBean {
    private String name; //名字
    private String number; //号码


    public LocalBean() {
        //空传构造函数
    }
    public LocalBean(String name, String number) {
//        全传构造函数
        this.name = name;
        this.number = number;

    }
    public String getName() {
        return name;
    }

    public void setName(String id) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String song) {
        this.number = number;
    }
}
