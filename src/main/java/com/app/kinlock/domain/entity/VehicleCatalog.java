package com.app.kinlock.domain.entity;

import com.app.kinlock.common.enums.VehicleClassificationEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleCatalog extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;

    @Enumerated(EnumType.STRING)
    VehicleClassificationEnum classifications;

    private String model;
    private Boolean highEnd;
}
