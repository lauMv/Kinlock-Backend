package com.app.kinlock.domain.implement;

import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.data.VehicleRepository;
import com.app.kinlock.domain.entity.Vehicle;
import com.app.kinlock.domain.mapper.VehicleMapper;
import com.app.kinlock.domain.service.VehicleService;
import com.app.kinlock.presentation.dto.VehicleDto;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VehicleServiceImpl extends CRUDServiceImpl<Vehicle, Integer> implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper mapper;

    @Override
    protected GenericRepository<Vehicle, Integer> getRepository() {
        return vehicleRepository;
    }

    @Override
    public Vehicle create(VehicleDto dto) {
        Vehicle vehicle = mapper.fromDto(dto, new Vehicle());
        this.create(vehicle);
        return vehicle;
    }

    @SneakyThrows
    @Override
    public Vehicle update(Integer id, VehicleDto dto) {
        Vehicle vehicle = this.getById(id);
        this.create(mapper.fromDto(dto, vehicle));
        return vehicle;
    }

}
