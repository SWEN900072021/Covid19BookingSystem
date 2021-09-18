package com.example.covid19bookingsystem.utils;

public class EnumUtils {

    public enum Gender {
        MALE,
        FEMALE,
        UNDISCLOSED
    }

    public enum AccountType {
        VR,
        HCP,
        ADMIN
    }

    public enum HealthCareProviderType {
        CLINIC,
        HOSPITAL,
        GP,
        POPUP,
        OTHER
    }

    public enum TimeslotStatus {
        UNBOOKED,
        BOOKED
    }

}
