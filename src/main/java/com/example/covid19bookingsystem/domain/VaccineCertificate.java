package com.example.covid19bookingsystem.domain;

import com.example.covid19bookingsystem.utils.EnumUtils.VaccineType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class VaccineCertificate {

    private Integer id;

    private Integer vaccineRecipient;

    private Integer healthCareProvider;

    private VaccineType vaccinationType;

    private Date dateIssued;
}

