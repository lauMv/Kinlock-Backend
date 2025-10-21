package com.app.kinlock.data;

import com.app.kinlock.common.enums.BenefitsCoverageEnum;
import com.app.kinlock.domain.entity.Benefit;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefitRepository extends GenericRepository<Benefit, Integer> {

    Boolean existsByNameAndCoverageAndIdNot(String name, BenefitsCoverageEnum coverage, Integer id);
}
