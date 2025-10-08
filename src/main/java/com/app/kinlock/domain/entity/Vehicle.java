package com.app.kinlock.domain.entity;

import com.app.kinlock.common.enums.VehicleClassificationEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;

    @Enumerated(EnumType.STRING)
    VehicleClassificationEnum classifications;

    private String model;
    private Integer year;
    private Boolean highEnd;
    private String name;
}
