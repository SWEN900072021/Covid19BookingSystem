package com.example.covid19bookingsystem.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Address {

    private String addressLine1;

    private String addressLine2;

    private String postcode;

    private String state;

    private String country;
}
