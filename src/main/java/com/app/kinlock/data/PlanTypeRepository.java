package com.app.kinlock.data;

import com.app.kinlock.domain.entity.PlanType;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanTypeRepository extends GenericRepository<PlanType, Integer> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);

    PlanType findByName(String name);
}