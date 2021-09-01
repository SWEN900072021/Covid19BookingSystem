package com.example.covid19bookingsystem.domain;

import com.example.covid19bookingsystem.utils.EnumUtils.VaccineType;
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

    private Integer healthcareProvider;

    private Integer questionnaire;

    private VaccineType vaccinationType;

    private Timestamp dateTime;

    private Integer duration;

    private String location;
}
