package com.app.kinlock.domain.implement;

import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.data.VehicleCatalogRepository;
import com.app.kinlock.domain.entity.VehicleCatalog;
import com.app.kinlock.domain.mapper.VehicleCatalogMapper;
import com.app.kinlock.domain.service.VehicleCatalogService;
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

    @Override
    protected GenericRepository<VehicleCatalog, Integer> getRepository() {
        return vehicleCatalogRepository;
    }

    @Override
    public VehicleCatalog create(VehicleDto dto) {
        VehicleCatalog vehicleCatalog = mapper.fromDto(dto, new VehicleCatalog());
        this.create(vehicleCatalog);
        return vehicleCatalog;
    }

    @SneakyThrows
    @Override
    public VehicleCatalog update(Integer id, VehicleDto dto) {
        VehicleCatalog vehicleCatalog = this.getById(id);
        this.create(mapper.fromDto(dto, vehicleCatalog));
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

}
