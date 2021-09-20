package com.example.covid19bookingsystem.domain;

import com.example.covid19bookingsystem.mapper.HealthCareProviderMapper;
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

    public Integer getOrganisationalId() {
        if (this.organisationalId == null) {
            load();
        }
        return this.organisationalId;
    }

    public String getHealthCareProviderName() {
        if (this.healthCareProviderName == null) {
            load();
        }
        return this.healthCareProviderName;
    }

    public HealthCareProviderType getHealthCareProviderType() {
        if (this.healthCareProviderType == null) {
            load();
        }
        return this.healthCareProviderType;
    }

    public String getPostcode() {
        if (this.postcode == null) {
            load();
        }
        return this.postcode;
    }

    private void load() {
        HealthCareProvider hcp = HealthCareProviderMapper.findHealthCareProviderById(this.id);
        if (this.organisationalId == null) {
            this.organisationalId = hcp.getOrganisationalId();
        }
        if (this.healthCareProviderName == null) {
            this.healthCareProviderName = hcp.getHealthCareProviderName();
        }
        if (this.healthCareProviderType == null) {
            this.healthCareProviderType = hcp.getHealthCareProviderType();
        }
        if (this.postcode == null) {
            this.postcode = hcp.getPostcode();
        }
    }
}
