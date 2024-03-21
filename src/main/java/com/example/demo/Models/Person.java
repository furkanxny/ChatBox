package com.example.demo.Models;

public class Person {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private int age;

    private int messageCount;

    public Person(String name, String lastName, String email, String password, int age, int messageCount) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.messageCount = messageCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMessageCount () {
        return messageCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}