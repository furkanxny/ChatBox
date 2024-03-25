package com.example.demo.Models;

public class Person {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private int age;
    private int gpt1, gpt2, gpt3, gpt4, gpt5, gpt6, gpt7, gpt8, gpt9, gpt10, gpt11, gpt12;

    private int messageCount;

    public Person(String name, String lastName, String email, String password, int age, int messageCount, int gpt1, int gpt2, int gpt3, int gpt4, int gpt5, int gpt6, int gpt7, int gpt8, int gpt9, int gpt10, int gpt11, int gpt12) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.messageCount = messageCount;
        this.gpt1 = gpt1;
        this.gpt2 = gpt2;
        this.gpt3 = gpt3;
        this.gpt4 = gpt4;
        this.gpt5 = gpt5;
        this.gpt6 = gpt6;
        this.gpt7 = gpt7;
        this.gpt8 = gpt8;
        this.gpt9 = gpt9;
        this.gpt10 = gpt10;
        this.gpt11 = gpt11;
        this.gpt12 = gpt12;
    }



    public int getGpt1(){return gpt1;}
    public void setGpt1(int gpt1){this.gpt1 = gpt1;}


    public int getGpt2(){return gpt2;}
    public void setGpt2(int gpt2){this.gpt2 = gpt2;}


    public int getGpt3(){return gpt3;}
    public void setGpt3(int gpt3){this.gpt3 = gpt1;}


    public int getGpt4(){return gpt4;}
    public void setGpt4(int gpt4){this.gpt1 = gpt4;}

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