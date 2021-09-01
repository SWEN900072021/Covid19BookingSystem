package com.example.covid19bookingsystem.domain;

import com.example.covid19bookingsystem.utils.EnumUtils;

import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor

public class VaccineRecipient {
    private int id;
    private int account;
    private String first_name;
    private String last_name;
    private String address;
    private Date date_of_birth;
    private String gender;
    private int phone_number;
    private String email;
    private EnumUtils vaccine_status;
    private EnumUtils vaccine_type;

    public VaccineRecipient(int id, int account, String first_name, String last_name, String address, Date date_of_birth, String gender,
                            int phone_number, String email, EnumUtils vaccine_status, EnumUtils vaccine_type){
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

}