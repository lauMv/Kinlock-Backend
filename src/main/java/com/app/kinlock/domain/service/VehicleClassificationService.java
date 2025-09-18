package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entities.VehicleClassification;

public interface VehicleClassificationService extends CRUDService<VehicleClassification, Integer> {

    VehicleClassification create(VehicleClassificationDto dto);
}
