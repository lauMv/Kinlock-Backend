package com.app.kinlock.domain.mapper;

import com.app.kinlock.common.enums.VehicleClassificationEnum;
import com.app.kinlock.domain.entity.VehicleCatalog;
import com.app.kinlock.presentation.dto.VehicleDto;
import org.springframework.stereotype.Component;

@Component
public class VehicleCatalogMapper {

    public VehicleCatalog fromDto(VehicleDto dto, VehicleCatalog vehicleCatalog){
        if (dto.getState() != null) {
            vehicleCatalog.setActive(dto.getState());
        }
        vehicleCatalog.setModel(dto.getModel());
        vehicleCatalog.setBrand(dto.getBrand());
        vehicleCatalog.setHighEnd(dto.getHighEnd());
        vehicleCatalog.setClassifications(VehicleClassificationEnum.fromString(dto.getClassification()));
        return vehicleCatalog;
    }
}
