package com.example.covid19bookingsystem;

import java.time.LocalDate;

public class VaccineCertificate {
    private int id;
    private String firstName;
    private String lastName;
    private VaccineType vaccineType;
    private LocalDate dateCreated;

    public VaccineCertificate(int id, VaccineRecipient vaccineRecipient){
        this.id = id;
        this.firstName = vaccineRecipient.getFirst_name();
        this.lastName = vaccineRecipient.getLast_name();
        this.vaccineType = vaccineRecipient.getVaccine_type();
        this.dateCreated = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public String getfirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public VaccineType getVaccineType() {
        return vaccineType;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }
}

