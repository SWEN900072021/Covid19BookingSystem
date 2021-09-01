package com.example.covid19bookingsystem.utils;

public class EnumUtils {

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }

    public enum VaccineStatus {
        NOT_VACCINATED,
        PARTIALLY_VACCINATED,
        FULLY_VACCINATED
    }

    public enum VaccineType {
        ASTRAZENECA,
        PFIZER
    }

    public enum AccountType {
        VR,
        HCP,
        ADMIN
    }

    public enum HcpType {
        CLINIC,
        HOSPITAL,
        GP,
        POPUP,
        OTHER
    }

    public enum Outcome {
        PASS,
        FAIL
    }

}
