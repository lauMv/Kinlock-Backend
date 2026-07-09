package com.app.kinlock.presentation.dto;

import com.app.kinlock.presentation.pojo.LimitPojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanBenefitDto {

    private Integer planId;
    private Integer benefitId;
    private String description;
}
