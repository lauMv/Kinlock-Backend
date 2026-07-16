package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.VehicleType;
import com.app.kinlock.presentation.dto.VehicleTypeDto;

public interface VehicleTypeService extends CRUDService<VehicleType, Integer> {

    VehicleType create(VehicleTypeDto dto);

    VehicleType update(Integer id, VehicleTypeDto dto);

    VehicleType getByName(String name);

}