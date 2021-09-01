package com.example.covid19bookingsystem.domain;

import com.example.covid19bookingsystem.utils.EnumUtils;
import com.example.covid19bookingsystem.utils.EnumUtils.VaccineType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
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
