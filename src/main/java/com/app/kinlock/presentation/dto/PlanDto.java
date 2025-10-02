package com.app.kinlock.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDto {

    private Integer vehicleId;

    private double discount;

    private double total;
}
