package com.app.kinlock.data;

import com.app.kinlock.domain.entity.VehicleType;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends GenericRepository<VehicleType, Integer> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);

    VehicleType findByName(String name);
}