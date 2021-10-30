package com.example.sqliteapp;

public class UserModel {

    private int id;
    private String name;
    private int age;
    private boolean isactive;


    //constructors
    public UserModel(int id, String name, int age, boolean isactive) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isactive = isactive;
    }

    public UserModel() {
    }


    //toString


    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isactive=" + isactive +
                '}';
    }

    //getters and setters
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
}
