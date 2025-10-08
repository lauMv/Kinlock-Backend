package com.app.kinlock.data;

import com.app.kinlock.domain.entity.Vehicle;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends GenericRepository<Vehicle, Integer> {
}
