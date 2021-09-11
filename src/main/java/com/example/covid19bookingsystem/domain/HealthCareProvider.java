package com.example.covid19bookingsystem.domain;

import com.example.covid19bookingsystem.utils.EnumUtils.HealthCareProviderType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class HealthCareProvider extends Account {

    private Integer id;

    private Integer organisationalId;

    private String healthCareProviderName;

    private HealthCareProviderType healthCareProviderType;

    private String postcode;

}
