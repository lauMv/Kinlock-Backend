package com.app.kinlock.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanBenefitDto {

    private Integer planId;
    private Integer benefitId;
    private Map<String, Double> limits;
}
