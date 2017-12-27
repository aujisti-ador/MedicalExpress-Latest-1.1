package com.ador.medicalexpress.models;

/**
 * Created by ADOR'S Lappy on 11-Oct-17.
 */

public class BloodRequestClass {
    private String name;
    private String bloodGroup;
    private String location;
    private String phoneNumber;
    private String date;

    public BloodRequestClass(String name, String bloodGroup, String location, String phoneNumber, String date) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getLocation() {
        return location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDate() {
        return date;
    }
}
