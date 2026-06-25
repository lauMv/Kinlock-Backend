package com.app.kinlock.data;

import com.app.kinlock.domain.entity.VehicleCatalog;
import com.app.kinlock.presentation.pojo.VehiclePojo;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleCatalogRepository extends GenericRepository<VehicleCatalog, Integer> {

    @Query("SELECT new com.app.kinlock.presentation.pojo.VehiclePojo(" +
            "v.id, v.brand, v.classification, v.model, v.highEnd, v. isElectric) " +
            "FROM VehicleCatalog v " +
            "WHERE v.id = :id")
    VehiclePojo findPojoById(Integer id);

    @Query("SELECT new com.app.kinlock.presentation.pojo.VehiclePojo(" +
            "v.id, v.brand, v.classification, v.model, v.highEnd, v. isElectric) " +
            "FROM VehicleCatalog v ")
    List<VehiclePojo> findAllPojo();

    @Query("SELECT DISTINCT v.brand FROM VehicleCatalog v WHERE v.brand IS NOT NULL ORDER BY v.brand")
    List<String> findAllBrands();

    @Query("SELECT DISTINCT v.model FROM VehicleCatalog v WHERE v.brand = :brand AND v.model IS NOT NULL ORDER BY v.model")
    List<String> findAllModelsByBrands(String brand);
}
