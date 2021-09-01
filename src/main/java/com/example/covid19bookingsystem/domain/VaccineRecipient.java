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
    private EnumUtils.VaccineStatus vaccine_status;
    private EnumUtils.VaccineType vaccine_type;

}