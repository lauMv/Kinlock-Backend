package com.app.kinlock.data;

import com.app.kinlock.domain.entities.VehicleClassification;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleClassificationRepository extends GenericRepository<VehicleClassification, Integer> {
}
