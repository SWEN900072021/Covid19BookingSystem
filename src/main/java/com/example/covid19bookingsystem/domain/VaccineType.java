package com.example.covid19bookingsystem.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class VaccineType {

    private String vaccineName;

    private String vaccineClass;

    private Integer minAge;

    private Integer maxAge;

    private Integer numberDoses;

    private Boolean bloodProblemRestriction;
}