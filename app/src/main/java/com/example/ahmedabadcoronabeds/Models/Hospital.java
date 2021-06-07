package com.example.ahmedabadcoronabeds.Models;

public class Hospital
{
    private String Name,Code,Address,Category,LastUpdateOn;
    private double PhoneNo;
    private double TO2;
    private double VO2;
    private double TIB;
    private double VIB;
    private double TICU;
    private double VICU;
    private double TICUV;
    private double VICUV;
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

    public double getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        PhoneNo = phoneNo;
    }

    public double getTO2() {
        return TO2;
    }

    public void setTO2(double TO2) {
        this.TO2 = TO2;
    }

    public double getVO2() {
        return VO2;
    }

    public void setVO2(double VO2) {
        this.VO2 = VO2;
    }

    public double getTIB() {
        return TIB;
    }

    public void setTIB(double TIB) {
        this.TIB = TIB;
    }

    public double getVIB() {
        return VIB;
    }

    public void setVIB(double VIB) {
        this.VIB = VIB;
    }

    public double getTICU() {
        return TICU;
    }

    public void setTICU(double TICU) {
        this.TICU = TICU;
    }

    public double getVICU() {
        return VICU;
    }

    public void setVICU(double VICU) {
        this.VICU = VICU;
    }

    public double getTICUV() {
        return TICUV;
    }

    public void setTICUV(double TICUV) {
        this.TICUV = TICUV;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "Name='" + Name + '\'' +
                ", Code='" + Code + '\'' +
                ", Address='" + Address + '\'' +
                ", Category='" + Category + '\'' +
                ", LastUpdateOn='" + LastUpdateOn + '\'' +
                ", PhoneNo=" + PhoneNo +
                ", TO2=" + TO2 +
                ", VO2=" + VO2 +
                ", TIB=" + TIB +
                ", VIB=" + VIB +
                ", TICU=" + TICU +
                ", VICU=" + VICU +
                ", TICUV=" + TICUV +
                ", VICUV=" + VICUV +
                '}';
    }

    public double getVICUV() {
        return VICUV;
    }

    public void setVICUV(double VICUV) {
        this.VICUV = VICUV;
    }

    public Hospital(String name, String code, String address, String category, String lastUpdateOn, int phoneNo, int TO2, int VO2, int TIB, int VIB, int TICU, int VICU, int TICUV, int VICUV) {
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
}
