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
    private EnumUtils vaccineType;
    private LocalDate dateCreated;

    public VaccineCertificate(int id, VaccineRecipient vaccineRecipient){
        this.id = id;
        this.firstName = vaccineRecipient.getFirst_name();
        this.lastName = vaccineRecipient.getLast_name();
        this.vaccineType = vaccineRecipient.getVaccine_type();
        this.dateCreated = LocalDate.now();
    }

}

