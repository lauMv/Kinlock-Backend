package com.app.kinlock.presentation.pojo;

import com.app.kinlock.common.enums.VehicleClassificationEnum;
import lombok.Data;

@Data
public class VehiclePojo {

    private Integer id;
    private String brand;
    private String classification;
    private String model;
    private Boolean highEnd;
    private Boolean isElectric;

    public VehiclePojo(Integer id, String brand, VehicleClassificationEnum classification, String model, Boolean highEnd, Boolean isElectric) {
        this.id = id;
        this.brand = brand;
        this.classification = classification.getValue();
        this.model = model;
        this.highEnd = highEnd;
        this.isElectric = isElectric;
    }
}
