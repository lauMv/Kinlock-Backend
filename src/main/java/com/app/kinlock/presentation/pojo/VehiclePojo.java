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
    private String vehicleType;
    private String engineType;
    private String segment;

    public VehiclePojo(Integer id, String brand, VehicleClassificationEnum classification, String model, String segment, String vehicleType, EngineTypeEnum engineType) {
        this.id = id;
        this.brand = brand;
        this.classification = classification != null ? classification.getValue() : null;
        this.model = model;
        this.segment = segment;
        this.vehicleType = vehicleType;
        this.engineType = engineType != null ? engineType.getValue() : null;
    }
}
