package com.example.ahmedabadcoronabeds.Models;

public class Hospital
{
    private String hospital_name,available,icu_b,hospital_address,hospital_contactno,
            total_available,time,total_icu_b,code,time_icu_b;

    public Hospital()
    {

    }

    public Hospital(String hospital_name, String available, String icu_b, String hospital_address, String hospital_contactno, String total_available, String time, String total_icu_b, String code, String time_icu_b)
    {
        this.hospital_name = hospital_name;
        this.available = available;
        this.icu_b = icu_b;
        this.hospital_address = hospital_address;
        this.hospital_contactno = hospital_contactno;
        this.total_available = total_available;
        this.time = time;
        this.total_icu_b = total_icu_b;
        this.code = code;
        this.time_icu_b = time_icu_b;
    }

    public String getTime_icu_b() {
        return time_icu_b;
    }

    public void setTime_icu_b(String time_icu_b) {
        this.time_icu_b = time_icu_b;
    }

    public String getTotal_icu_b() {
        return total_icu_b;
    }

    public void setTotal_icu_b(String total_icu_b) {
        this.total_icu_b = total_icu_b;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTotal_available()
    {
        return total_available;
    }

    public void setTotal_available(String total_available) {
        this.total_available = total_available;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHospital_address()
    {
        return hospital_address;
    }

    public void setHospital_address(String hospital_address) {
        this.hospital_address = hospital_address;
    }

    public String getHospital_contactno() {
        return hospital_contactno;
    }

    public void setHospital_contactno(String hospital_contactno) {
        this.hospital_contactno = hospital_contactno;
    }

    public String getIcu_b() {
        return icu_b;
    }

    public void setIcu_b(String icu_b) {
        this.icu_b = icu_b;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }
}
