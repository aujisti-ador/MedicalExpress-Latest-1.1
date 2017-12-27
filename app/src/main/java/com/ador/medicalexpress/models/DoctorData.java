package com.ador.medicalexpress.models;

/**
 * Created by DORBESH on 1/7/2017.
 */

public class DoctorData {

    String name;
    String designation;
    String qualification;
    String chamber;
    String location;
    String phone;
    String speciality;
    String email;

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }


    String web;

    public DoctorData(){}
    public DoctorData(String name){this.name = name;}
    public DoctorData(String name, String designation, String qualification, String chamber, String location, String phone) {
        this.name = name;
        this.designation = designation;
        this.qualification = qualification;
        this.chamber = chamber;
        this.location = location;
        this.phone = phone;
    }

    public DoctorData(String name, String designation, String qualification, String chamber, String location, String phone, String speciality, String email, String web) {

        this.name = name;
        this.speciality = speciality;
        this.qualification = qualification;
        this.designation = designation;
        this.chamber = chamber;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.web = web;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
