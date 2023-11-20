package com.example.grishaee.Models;

public class User {
    private Long id;
    private String secondName, firstName, lastName, email, phone, userStatus;

    //  Конструкторы

    public User() {
    }

    public User(Long id, String secondName, String firstName, String lastName, String email, String phone, String userStatus) {
        this.id = id;
        this.secondName = secondName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.userStatus = userStatus;
    }

    public User(String secondName, String firstName, String lastName, String email, String phone, String userStatus) {
        this.secondName = secondName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.userStatus = userStatus;
    }

    //  Гетеры

    public Long getId() {
        return id;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUserStatus() {
        return userStatus;
    }

    //  Сетеры

    public void setId(Long id) {
        this.id = id;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    //  toString

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", secondName='" + secondName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", userStatus='" + userStatus + '\'' +
                '}';
    }
}
