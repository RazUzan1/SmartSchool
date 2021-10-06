package com.example.smartschool.objects;

import java.util.ArrayList;

public class UserObject {
    private String firstName,lastName,password,classId,userName;
    private ArrayList<String>userPersonalNotifications;

    public UserObject(String firstName,
                      String lastName, String password,
                      String classId, String userName,
                      ArrayList<String> userPersonalNotifications) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.classId = classId;
        this.userName = userName;
        this.userPersonalNotifications = userPersonalNotifications;
    }

    public UserObject() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<String> getUserPersonalNotifications() {
        return userPersonalNotifications;
    }

    public void setUserPersonalNotifications(ArrayList<String> userPersonalNotifications) {
        this.userPersonalNotifications = userPersonalNotifications;
    }
}
