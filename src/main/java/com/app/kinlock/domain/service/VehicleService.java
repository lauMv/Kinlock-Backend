package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.Vehicle;
import com.app.kinlock.presentation.dto.VehicleDto;

public interface VehicleService extends CRUDService<Vehicle, Integer> {

    Vehicle create(VehicleDto dto);
}
