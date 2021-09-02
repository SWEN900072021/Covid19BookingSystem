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
    private String first_name;
    private String last_name;
    private String address;
    private Date date_of_birth;
    private String gender;
    private Integer phone_number;
    private String email;
    private VaccineStatus vaccine_status;
    private VaccineType vaccine_type;
}
