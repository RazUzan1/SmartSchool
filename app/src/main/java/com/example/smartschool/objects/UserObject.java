package com.example.smartschool.objects;

public class UserObject {
    private String firstName,lastName,password,classId,userName;

    public UserObject(String firstName, String lastName, String password, String classId, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.classId = classId;
        this.userName = userName;
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
}
