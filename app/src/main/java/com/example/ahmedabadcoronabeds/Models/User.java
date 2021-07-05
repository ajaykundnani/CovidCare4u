package com.example.ahmedabadcoronabeds.Models;

public class User {
    private String Name, Password, Role, HospitalCode, MobileNo, Age;
    public User() {
    }

    public User(String name, String password, String role, String hospitalCode, String mobileNo, String age) {
        Name = name;
        Password = password;
        Role = role;
        HospitalCode = hospitalCode;
        MobileNo = mobileNo;
        Age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getHospitalCode() {
        return HospitalCode;
    }

    public void setHospitalCode(String HospitalCode) {
        this.HospitalCode = HospitalCode;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String MobileNo) {
        this.MobileNo = MobileNo;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", Password='" + Password + '\'' +
                ", Role='" + Role + '\'' +
                ", HospitalCode='" + HospitalCode + '\'' +
                ", MobileNo='" + MobileNo + '\'' +
                ", Age='" + Age + '\'' +
                '}';
    }
}
