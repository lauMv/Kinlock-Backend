package com.app.kinlock.presentation.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {

    @Nullable
    private Boolean state;
    private String brand;
    private String classification;
    private String model;
    private Integer year;
    private Boolean highEnd;
}
