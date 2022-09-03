package com.example.resturentadminside.SignInAnd_SignUp_Releated_Classes;

import com.google.firebase.database.Exclude;

public class Upload {

    private String mKey;
    private String user_ImageUrl;
    private String resturent_name;
    public  String location;
    public  String zone;
    public  String user_mobile_number;
    public  String user_password;
    public String provide_meal;
    public String provide_fasfood;


    public Upload() {
    }

    public Upload(String user_ImageUrl, String resturent_name,
                  String location, String zone, String user_mobile_number,
                  String user_password, String provide_meal,
                  String provide_fasfood) {
        this.user_ImageUrl = user_ImageUrl;
        this.resturent_name = resturent_name;
        this.location = location;
        this.zone = zone;
        this.user_mobile_number = user_mobile_number;
        this.user_password = user_password;
        this.provide_meal = provide_meal;
        this.provide_fasfood = provide_fasfood;
    }

    public String getUser_ImageUrl() {
        return user_ImageUrl;
    }

    public void setUser_ImageUrl(String user_ImageUrl) {
        this.user_ImageUrl = user_ImageUrl;
    }

    public String getResturent_name() {
        return resturent_name;
    }

    public void setResturent_name(String resturent_name) {
        this.resturent_name = resturent_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getUser_mobile_number() {
        return user_mobile_number;
    }

    public void setUser_mobile_number(String user_mobile_number) {
        this.user_mobile_number = user_mobile_number;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getProvide_meal() {
        return provide_meal;
    }

    public void setProvide_meal(String provide_meal) {
        this.provide_meal = provide_meal;
    }

    public String getProvide_fasfood() {
        return provide_fasfood;
    }

    public void setProvide_fasfood(String provide_fasfood) {
        this.provide_fasfood = provide_fasfood;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        mKey = key;
    }
}
