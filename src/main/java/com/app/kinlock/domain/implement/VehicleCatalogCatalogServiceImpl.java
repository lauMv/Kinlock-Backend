package com.app.kinlock.domain.implement;

import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.data.VehicleCatalogRepository;
import com.app.kinlock.domain.entity.VehicleCatalog;
import com.app.kinlock.domain.mapper.VehicleCatalogMapper;
import com.app.kinlock.domain.service.SegmentService;
import com.app.kinlock.domain.service.VehicleCatalogService;
import com.app.kinlock.domain.service.VehicleTypeService;
import com.app.kinlock.exceptions.EntityNotFoundException;
import com.app.kinlock.presentation.dto.VehicleDto;
import com.app.kinlock.presentation.pojo.VehiclePojo;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VehicleCatalogCatalogServiceImpl extends CRUDServiceImpl<VehicleCatalog, Integer> implements VehicleCatalogService {

    private final VehicleCatalogRepository vehicleCatalogRepository;
    private final VehicleCatalogMapper mapper;
    private final VehicleTypeService vehicleTypeService;
    private final SegmentService segmentService;

    @Override
    protected GenericRepository<VehicleCatalog, Integer> getRepository() {
        return vehicleCatalogRepository;
    }

    @Override
    public VehicleCatalog create(VehicleDto dto) {
        VehicleCatalog vehicleCatalog = mapper.fromDto(dto, new VehicleCatalog());
        vehicleCatalog.setVehicleType(vehicleTypeService.getByName(dto.getVehicleType()));
        vehicleCatalog.setSegment(segmentService.getByName(dto.getSegment()));
        this.create(vehicleCatalog);
        return vehicleCatalog;
    }

    @SneakyThrows
    @Override
    public VehicleCatalog update(Integer id, VehicleDto dto) {
        VehicleCatalog vehicleCatalog = this.getById(id);
        vehicleCatalog = mapper.fromDto(dto, vehicleCatalog);
        vehicleCatalog.setVehicleType(vehicleTypeService.getByName(dto.getVehicleType()));
        this.create(vehicleCatalog);
        return vehicleCatalog;
    }

    @Override
    public VehiclePojo getPojoById(Integer id) {
        return vehicleCatalogRepository.findPojoById(id);
    }

    @Override
    public List<VehiclePojo> getAllPojo() {
        return vehicleCatalogRepository.findAllPojo();
    }

    @Override
    public List<String> getAllBrands() {
        return vehicleCatalogRepository.findAllBrands();
    }

    @Override
    public List<String> getAllModelsByBrand(String brand) {
        return vehicleCatalogRepository.findAllModelsByBrands(brand);
    }

    @Override
    public VehicleCatalog getByBrandAndModel(String brand, String model) {
        VehicleCatalog vehicle = vehicleCatalogRepository.findByBrandIgnoreCaseAndModelIgnoreCase(brand, model);
        if (vehicle != null) {
            return vehicle;
        } else {
            throw new EntityNotFoundException("Vehiculo no encontrado");
        }

    }

}
