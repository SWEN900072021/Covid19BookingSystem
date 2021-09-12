package com.example.covid19bookingsystem.domain;

import com.example.covid19bookingsystem.utils.EnumUtils.VaccineStatus;
import com.example.covid19bookingsystem.utils.EnumUtils.VaccineType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class VaccineRecipient extends Account {

    private Integer id;

    private String firstName;

    private String lastName;

    private String address;

    private Date dateOfBirth;

    private String gender;

    private String phoneNumber;

    private String email;

    private VaccineStatus vaccineStatus;

    private VaccineType vaccineType;

}