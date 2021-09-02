package com.example.covid19bookingsystem.domain;

import com.example.covid19bookingsystem.utils.EnumUtils.HcpType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HealthCareProvider {
    private Integer organisationalId;
    private Integer account;
    private String name;
    private HcpType hcpType;
    private Integer postcode;
}
