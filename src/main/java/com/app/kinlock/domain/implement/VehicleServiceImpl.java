package com.app.kinlock.domain.implement;

import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.domain.entities.Vehicle;
import com.app.kinlock.domain.service.VehicleService;

public class VehicleServiceImpl extends CRUDServiceImpl<Vehicle, Integer> implements VehicleService {
    @Override
    protected GenericRepository<Vehicle, Integer> getRepository() {
        return null;
    }

    @Override
    public Vehicle create(VehicleDto dto) {
        return null;
    }
}
