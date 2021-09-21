package com.example.covid19bookingsystem.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Question {

    private Integer id;

    private String question;

    private Boolean successAnswer;
}
