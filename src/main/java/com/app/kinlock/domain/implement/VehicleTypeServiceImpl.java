package com.app.kinlock.domain.implement;

import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.data.VehicleTypeRepository;
import com.app.kinlock.domain.entity.VehicleType;
import com.app.kinlock.domain.service.VehicleTypeService;
import com.app.kinlock.exceptions.DuplicatedException;
import com.app.kinlock.presentation.dto.VehicleTypeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VehicleTypeServiceImpl extends CRUDServiceImpl<VehicleType, Integer> implements VehicleTypeService {

    private final VehicleTypeRepository repository;

    @Override
    protected GenericRepository<VehicleType, Integer> getRepository() {
        return repository;
    }

    @Override
    public VehicleType create(VehicleTypeDto dto) {
        if (repository.existsByName(dto.getName())) {
            throw new DuplicatedException("El tipo de vehiculo " + dto.getName() + " ya existe");
        }
        VehicleType vehicleType = new VehicleType();
        vehicleType.setName(dto.getName());
        return this.create(vehicleType);
    }

    @Override
    public VehicleType update(Integer id, VehicleTypeDto dto) {
        VehicleType vehicleType = this.getById(id);
        if (repository.existsByNameAndIdNot(dto.getName(), id)) {
            throw new DuplicatedException("El tipo de vehiculo " + dto.getName() + " ya existe");
        }
        vehicleType.setName(dto.getName());
        return repository.save(vehicleType);
    }

    @Override
    public VehicleType getByName(String name) {
        return repository.findByName(name);
    }
}