package com.app.kinlock.presentation.dto;

import lombok.Data;

@Data
public class FilterPlanDto {

    private String vehicleType;
    private String engineType;
    private String brand;
    private String model;
    private String classification;
    private Integer year;
    private String vehicleValue;
    private String regional;
    private String franchise;
    private String segment;
    private String planType;
}
