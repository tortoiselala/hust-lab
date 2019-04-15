package com.tortoiselala.course.java.lab2.base;

public class User {
    private String username;
    private String password;
    // 如果在数据库中查询到当前用户，permission为true，否则为false
    private boolean permission;
    // 如果符合登录条件，医生则为true，病人为false
    private boolean docterOrPatient;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.check();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isDocterOrPatient() {
        return docterOrPatient;
    }

    public boolean isPermission() {
        return permission;
    }

    private void check(){
        this.permission = DatabaseManager.checkUserPermission(this);
        this.docterOrPatient = DatabaseManager.checkUserPatientOrDoctor(this);
    }
}
