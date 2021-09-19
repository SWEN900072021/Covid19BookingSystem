package com.example.covid19bookingsystem.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class VaccineCertificate {

    private VaccineRecipient vaccineRecipient;

    private VaccineType vaccineType;
}