package com.example.ahmedabadcoronabeds.Models;

import java.io.Serializable;

public class Hospital implements Serializable
{
    private String Name,Code,Address,Category,LastUpdateOn,PhoneNo,TO2,VO2,TIB,VIB,TICU,VICU,TICUV,VICUV;

    public Hospital(String name, String code, String address, String category, String lastUpdateOn, String phoneNo, String TO2, String VO2, String TIB, String VIB, String TICU, String VICU, String TICUV, String VICUV) {
        Name = name;
        Code = code;
        Address = address;
        Category = category;
        LastUpdateOn = lastUpdateOn;
        PhoneNo = phoneNo;
        this.TO2 = TO2;
        this.VO2 = VO2;
        this.TIB = TIB;
        this.VIB = VIB;
        this.TICU = TICU;
        this.VICU = VICU;
        this.TICUV = TICUV;
        this.VICUV = VICUV;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "Name='" + Name + '\'' +
                ", Code='" + Code + '\'' +
                ", Address='" + Address + '\'' +
                ", Category='" + Category + '\'' +
                ", LastUpdateOn='" + LastUpdateOn + '\'' +
                ", PhoneNo='" + PhoneNo + '\'' +
                ", TO2='" + TO2 + '\'' +
                ", VO2='" + VO2 + '\'' +
                ", TIB='" + TIB + '\'' +
                ", VIB='" + VIB + '\'' +
                ", TICU='" + TICU + '\'' +
                ", VICU='" + VICU + '\'' +
                ", TICUV='" + TICUV + '\'' +
                ", VICUV='" + VICUV + '\'' +
                '}';
    }

    public Hospital() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getLastUpdateOn() {
        return LastUpdateOn;
    }

    public void setLastUpdateOn(String lastUpdateOn) {
        LastUpdateOn = lastUpdateOn;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getTO2() {
        return TO2;
    }

    public void setTO2(String TO2) {
        this.TO2 = TO2;
    }

    public String getVO2() {
        return VO2;
    }

    public void setVO2(String VO2) {
        this.VO2 = VO2;
    }

    public String getTIB() {
        return TIB;
    }

    public void setTIB(String TIB) {
        this.TIB = TIB;
    }

    public String getVIB() {
        return VIB;
    }

    public void setVIB(String VIB) {
        this.VIB = VIB;
    }

    public String getTICU() {
        return TICU;
    }

    public void setTICU(String TICU) {
        this.TICU = TICU;
    }

    public String getVICU() {
        return VICU;
    }

    public void setVICU(String VICU) {
        this.VICU = VICU;
    }

    public String getTICUV() {
        return TICUV;
    }

    public void setTICUV(String TICUV) {
        this.TICUV = TICUV;
    }

    public String getVICUV() {
        return VICUV;
    }

    public void setVICUV(String VICUV) {
        this.VICUV = VICUV;
    }
}
