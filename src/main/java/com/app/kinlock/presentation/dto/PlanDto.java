package com.app.kinlock.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDto {

    private Integer vehicleCatalogId;

    private Integer regionalId;

    private Integer insuranceId;

    private Double minimumPremium;
    private Double rate;
    private Integer ageLimit;
    private Double discount;
}
