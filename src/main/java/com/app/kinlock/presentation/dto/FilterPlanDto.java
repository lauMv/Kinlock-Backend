package com.app.kinlock.presentation.dto;

import lombok.Data;

@Data
public class FilterPlanDto {

    private String brand;
    private String model;
    private Integer year;
    private String vehicleValue;
    private String regional;
    private String franchise;
    private String planType;

    // client info

    private String clientName;
    private Long clientPhone;
    private String clientEmail;

    // Populated internally from VehicleCatalog lookup — NOT sent by the client
    private String vehicleType;
    private String classification;
    private String engineType;
    private String segment;
}