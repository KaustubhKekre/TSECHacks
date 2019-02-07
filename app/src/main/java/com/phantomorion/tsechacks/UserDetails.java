package com.phantomorion.tsechacks;

public class UserDetails {
private String name,email,age,userType,gender;
Integer points,donations;


public UserDetails(){}
    public UserDetails(String name, String email, String age, String userType,String gender,Integer points ,Integer donations) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.userType = userType;
        this.gender=gender;
        this.points=points;
        this.donations=donations;
    }
    public Integer getPoints()
    {
        return points;
    }
    public void setPoints(Integer points)
    {
        this.points=points;
    }
    public Integer getDonations()
    {
        return donations;
    }
    public void setDonations(Integer donations)
    {
        this.donations=donations;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}