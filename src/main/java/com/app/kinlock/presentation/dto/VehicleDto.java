package com.app.kinlock.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {

    private String brand;
    private String classification;
    private String model;
    private Integer year;
    private Boolean highEnd;
}
