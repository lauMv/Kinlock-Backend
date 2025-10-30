package com.app.kinlock.presentation.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PlanBenefitPojo
{
    private Integer id;
    private Integer planId;
    private Integer benefitId;
    private String benefitName;
    private List<LimitPojo> limits;
}
