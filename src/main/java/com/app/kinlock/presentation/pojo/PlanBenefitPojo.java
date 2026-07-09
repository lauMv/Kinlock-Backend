package com.app.kinlock.presentation.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanBenefitPojo {
    private Integer id;
    private Integer planId;
    private Integer benefitId;
    private String benefitName;
    private Double price;
    private String description;
}
