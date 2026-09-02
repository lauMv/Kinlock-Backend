package com.app.kinlock.presentation.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDto {

    @Nullable
    private Boolean state;

    private String name;

    private Integer regionalId;

    private Integer insuranceId;

    private Double minimumPremium;

    private Double rate;

    private Integer ageLimit;

    private Integer segmentId;

    private String engineType;

    private Integer vehicleTypeId;

    private Integer planTypeId;

    private Double discount;

    private String franchise;

    private Double interest;

    private Integer brokerId;

}
