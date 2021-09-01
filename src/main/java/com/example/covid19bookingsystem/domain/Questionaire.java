package com.example.covid19bookingsystem.domain;

import java.time.LocalDate;

import com.example.covid19bookingsystem.utils.EnumUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor

public class Questionaire {
    private int id;
    private int personId;
    private String firstName;
    private String lastName;
    private String eligibility;
    private EnumUtils.Outcome result;
    private LocalDate dateTaken;

    public Questionaire(VaccineRecipient vaccineRecipient){
        this.dateTaken = LocalDate.now();
        this.personId = vaccineRecipient.getId();
        this.firstName = vaccineRecipient.getFirst_name();
        this.lastName = vaccineRecipient.getLast_name();
    }
}
