package com.example.covid19bookingsystem.domain;
import java.time.LocalDate;
import com.example.covid19bookingsystem.utils.EnumUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class VaccineCertificate {
    private int id;
    private String firstName;
    private String lastName;
    private EnumUtils.VaccineType vaccineType;
    private LocalDate dateCreated;

}

