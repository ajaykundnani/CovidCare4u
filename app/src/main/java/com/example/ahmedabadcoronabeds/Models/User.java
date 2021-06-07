package com.example.ahmedabadcoronabeds.Models;

public class User {
    private String Name,Password,Role,HospitalCode;
    private int MobileNo;

    public User() {
    }

    public User(String name, String password, String role, String hospitalCode, int mobileNo) {
        Name = name;
        Password = password;
        Role = role;
        HospitalCode = hospitalCode;
        MobileNo = mobileNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getHospitalCode() {
        return HospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        HospitalCode = hospitalCode;
    }

    public int getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(int mobileNo) {
        MobileNo = mobileNo;
    }
}
