package com.example.covid19bookingsystem;

import java.util.Date;

public class VaccineRecipient {
    // Variable Declaration
    private int id;
    private int account;
    private String first_name;
    private String last_name;
    private String address;
    private Date date_of_birth;
    private String gender;
    private int phone_number;
    private String email;
    private VaccineStatus vaccine_status;
    private VaccineType vaccine_type;

    public VaccineRecipient(int id, int account, String first_name, String last_name, String address, Date date_of_birth, String gender,
                            int phone_number, String email, VaccineStatus vaccine_status, VaccineType vaccine_type){
        this.id = id;
        this.account = account;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.phone_number = phone_number;
        this.email = email;
        this.vaccine_status = vaccine_status;
        this.vaccine_type = vaccine_type;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public int getAccount() {
        return account;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAddress() {
        return address;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public VaccineStatus getVaccine_status() {
        return vaccine_status;
    }

    public VaccineType getVaccine_type() {
        return vaccine_type;
    }

    public void setVaccine_status(VaccineStatus vaccine_status) {
        this.vaccine_status = vaccine_status;
    }

    public void setVaccine_type(VaccineType vaccine_type) {
        this.vaccine_type = vaccine_type;
    }
}