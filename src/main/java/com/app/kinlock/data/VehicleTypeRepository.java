package com.app.kinlock.data;

import com.app.kinlock.domain.entity.VehicleType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends GenericRepository<VehicleType, Integer> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);

    @Query("SELECT v FROM VehicleType v WHERE LOWER(v.name) = LOWER(:name)")
    VehicleType findByName(@Param("name") String name);
}