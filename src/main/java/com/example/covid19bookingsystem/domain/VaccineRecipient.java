package com.example.covid19bookingsystem.domain;

import com.example.covid19bookingsystem.utils.EnumUtils.Gender;
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

    private Address address;

    private Date dateOfBirth;

    private Gender gender;

    private String phoneNumber;

    private String email;
}