package com.nikita.springMVC.model;

import jakarta.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Name should be not empty")
    @Size(min = 2, max = 32, message = "Name should be between 2 and 32 characters long")
    private String name;
    @Min(value = 12, message = "You're small")
    private int age;
    @NotEmpty(message = "Email should be not empty")
    @Email
    private String email;

    public Person(){

    }

    public Person(String name, int id, int age, String email) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
