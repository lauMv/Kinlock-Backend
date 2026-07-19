package com.app.kinlock.data;

import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.presentation.pojo.PlanPojo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends GenericRepository<Plan, Integer> {

    @Query("SELECT new com.app.kinlock.presentation.pojo.PlanPojo ( " +
            "p.id, p.active, p.vehicleCatalog.id, p.regional.id, p.insurance.id, p.minimumPremium, p.rate, p.ageLimit, p.discount, p.franchise, p.segment.name, p.planType.name, p.broker.name) " +
            "FROM Plan p ")
    List<PlanPojo> getAllPojo();
}
