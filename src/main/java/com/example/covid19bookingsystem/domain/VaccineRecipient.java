package com.example.covid19bookingsystem.domain;


import com.example.covid19bookingsystem.utils.EnumUtils.VaccineStatus;
import com.example.covid19bookingsystem.utils.EnumUtils.VaccineType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
@RequiredArgsConstructor
public class VaccineRecipient {
    private Integer id;
    private Integer account;
    private String firstName;
    private String lastName;
    private String address;
    private Date dateOfBirth;
    private String gender;
    private Integer phoneNumber;
    private String email;
    private VaccineStatus vaccineStatus;
    private VaccineType vaccineType;
}
