package com.app.kinlock.presentation.pojo;

import com.app.kinlock.common.enums.EngineTypeEnum;
import com.app.kinlock.common.enums.VehicleClassificationEnum;
import lombok.Data;

@Data
public class VehiclePojo {

    private Integer id;
    private String brand;
    private String classification;
    private String model;
    private Boolean highEnd;
    private String vehicleType;
    private String engineType;

    public VehiclePojo(Integer id, String brand, VehicleClassificationEnum classification, String model, Boolean highEnd, String vehicleType, EngineTypeEnum engineType) {
        this.id = id;
        this.brand = brand;
        this.classification = classification != null ? classification.getValue() : null;
        this.model = model;
        this.highEnd = highEnd;
        this.vehicleType = vehicleType;
        this.engineType = engineType != null ? engineType.getValue() : null;
    }
}
