package com.app.kinlock.domain.implement;

import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.domain.entities.VehicleClassification;
import com.app.kinlock.domain.service.VehicleClassificationService;

public class VehicleClassificationServiceImpl extends CRUDServiceImpl<VehicleClassification, Integer> implements VehicleClassificationService {

    @Override
    protected GenericRepository<VehicleClassification, Integer> getRepository() {
        return null;
    }

    @Override
    public VehicleClassification create(VehicleClassificationDto dto) {
        return null;
    }
}
