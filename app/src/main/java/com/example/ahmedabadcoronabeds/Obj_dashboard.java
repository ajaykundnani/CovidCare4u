package com.example.ahmedabadcoronabeds;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;


public class Obj_dashboard {
    private static final String TAG = "User";

    private String TO2,VO2,TIB,VIB,TICU,VICU,TICUV,VICUV;

    public Obj_dashboard(String TO2, String VO2, String TIB, String VIB, String TICU, String VICU, String TICUV, String VICUV) {
        this.TO2 = TO2;
        this.VO2 = VO2;
        this.TIB = TIB;
        this.VIB = VIB;
        this.TICU = TICU;
        this.VICU = VICU;
        this.TICUV = TICUV;
        this.VICUV = VICUV;
    }

    public static String getTAG() {
        return TAG;
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

    @Override
    public String toString() {
        return "Obj_dashboard{" +
                "TO2='" + TO2 + '\'' +
                ", VO2='" + VO2 + '\'' +
                ", TIB='" + TIB + '\'' +
                ", VIB='" + VIB + '\'' +
                ", TICU='" + TICU + '\'' +
                ", VICU='" + VICU + '\'' +
                ", TICUV='" + TICUV + '\'' +
                ", VICUV='" + VICUV + '\'' +
                '}';
    }
}

