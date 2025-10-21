package com.app.kinlock.domain.mapper;

import com.app.kinlock.common.enums.VehicleClassificationEnum;
import com.app.kinlock.domain.entity.VehicleCatalog;
import com.app.kinlock.presentation.dto.VehicleDto;
import org.springframework.stereotype.Component;

@Component
public class VehicleCatalogMapper {

    public VehicleCatalog fromDto(VehicleDto dto, VehicleCatalog vehicleCatalog){
        vehicleCatalog.setModel(dto.getModel());
        vehicleCatalog.setBrand(dto.getModel());
        vehicleCatalog.setHighEnd(dto.getHighEnd());
        vehicleCatalog.setClassifications(VehicleClassificationEnum.fromString(dto.getClassification()));
        return vehicleCatalog;
    }
}
