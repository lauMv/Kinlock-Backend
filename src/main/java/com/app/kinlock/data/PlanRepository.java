package com.app.kinlock.data;

import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.presentation.pojo.PlanPojo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends GenericRepository<Plan, Integer> {

    @Query("SELECT new com.app.kinlock.presentation.pojo.PlanPojo ( " +
            "p.id, p.vehicleCatalog.id, p.vehicleCatalog.brand, p.vehicleCatalog.model, p.regional.name, p.insurance.name, p.minimumPremium, p.rate, p.discount) " +
            "FROM Plan p ")
    List<PlanPojo> getAllPojo();
}
