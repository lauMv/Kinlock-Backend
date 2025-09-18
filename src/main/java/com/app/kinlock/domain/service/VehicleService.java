package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entities.Vehicle;

public interface VehicleService extends CRUDService<Vehicle, Integer> {

    Vehicle create(VehicleDto dto);
}
