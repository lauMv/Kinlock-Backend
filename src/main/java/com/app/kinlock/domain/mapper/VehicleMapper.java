package com.app.kinlock.domain.mapper;

import com.app.kinlock.common.enums.VehicleClassificationEnum;
import com.app.kinlock.domain.entity.Vehicle;
import com.app.kinlock.presentation.dto.VehicleDto;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public Vehicle fromDto(VehicleDto dto, Vehicle vehicle){
        vehicle.setYear(dto.getYear());
        vehicle.setModel(dto.getModel());
        vehicle.setBrand(dto.getModel());
        vehicle.setHighEnd(dto.getHighEnd());
        vehicle.setName(dto.getBrand() + "-" + dto.getModel() + "-" + dto.getYear());
        vehicle.setClassifications(VehicleClassificationEnum.fromString(dto.getClassification()));
        return vehicle;
    }
}
