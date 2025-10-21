package com.app.kinlock.data;

import com.app.kinlock.domain.entity.VehicleCatalog;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleCatalogRepository extends GenericRepository<VehicleCatalog, Integer> {
}
