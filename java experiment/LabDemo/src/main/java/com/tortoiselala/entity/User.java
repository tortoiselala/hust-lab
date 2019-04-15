package com.tortoiselala.entity;

public class User {

    private String userId;

    private String password;

    private boolean doctor;

    public User() {
        this("", "", false);
    }

    public User(String userId, String password, boolean doctor) {
        this.userId = userId;
        this.password = password;
        this.doctor = doctor;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDoctor() {
        return doctor;
    }

    public void setDoctor(boolean doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", doctor=" + doctor +
                '}';
    }
}
