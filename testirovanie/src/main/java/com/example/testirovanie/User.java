package com.example.testirovanie;

public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String location;
    private String gender;

    private String allcallories;

    private Integer callories_acc;

    public User(String firstName,String lastName,String userName,String password,String location,String gender){
        this.firstName=firstName;
        this.lastName=lastName;
        this.userName=userName;
        this.password=password;
        this.location=location;
        this.gender=gender;

    }

    public User() {

    }
    public User(String allcallories) {
        this.allcallories=allcallories;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getCallories_acc() {
        return callories_acc;
    }

    public void setCallories_acc(Integer callories_acc) {
        this.callories_acc = callories_acc;
    }
}
