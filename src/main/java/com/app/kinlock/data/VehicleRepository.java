package com.app.kinlock.data;

import com.app.kinlock.domain.entities.Vehicle;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends GenericRepository<Vehicle, Integer> {
}
