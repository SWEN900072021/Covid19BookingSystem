package com.example.covid19bookingsystem.domain;

import com.example.covid19bookingsystem.utils.EnumUtils.HcpType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HealthCareProvider {

    @NonNull
    private Integer organisationalId;

    @NonNull
    private Integer account;

    private String name;

    @NonNull
    private HcpType hcpType;

    private Integer postcode;

}
