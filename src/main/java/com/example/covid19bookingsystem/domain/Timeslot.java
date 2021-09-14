package com.example.covid19bookingsystem.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
public class Timeslot {

    private Integer id;

    private Integer vaccineRecipient;

    private HealthCareProvider healthcareProvider = new HealthCareProvider();

    private Integer questionnaire;

    //need to validate that this is an existing row in VaccineType table?
    private String vaccineType;

    private String status;

    private Timestamp dateTime;

    private Integer duration;

    private Address address;
}
