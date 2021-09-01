package com.example.covid19bookingsystem;

import java.time.LocalDate;

public class Questionaire {
    private int id;
    private int personId;
    private String firstName;
    private String lastName;
    private String eligibility;
    private String result;
    private LocalDate dateTaken;

    public Questionaire(VaccineRecipient vaccineRecipient){
        this.dateTaken = LocalDate.now();
        this.personId = vaccineRecipient.getId();
        this.firstName = vaccineRecipient.getFirst_name();
        this.lastName = vaccineRecipient.getLast_name();
    }

    public LocalDate getDateTaken() {
        return dateTaken;
    }

    public int getPersonId() {
        return personId;
    }

    public String getEligibility() {
        return eligibility;
    }

    public String getResult() {
        return result;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
