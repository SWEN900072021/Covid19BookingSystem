package com.example.covid19bookingsystem.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class Questionnaire {

    private Integer id;

    private Date dateTaken;

    private String outcome;
}
