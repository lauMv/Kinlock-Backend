package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.VehicleCatalog;
import com.app.kinlock.presentation.dto.VehicleDto;
import com.app.kinlock.presentation.pojo.VehiclePojo;

import java.util.List;

public interface VehicleCatalogService extends CRUDService<VehicleCatalog, Integer> {

    VehicleCatalog create(VehicleDto dto);

    VehicleCatalog update(Integer id, VehicleDto dto);

    VehiclePojo getPojoById(Integer id);

    List<VehiclePojo> getAllPojo();
}
