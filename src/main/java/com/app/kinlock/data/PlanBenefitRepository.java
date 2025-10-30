package com.app.kinlock.data;

import com.app.kinlock.domain.entity.PlanBenefit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanBenefitRepository extends GenericRepository<PlanBenefit, Integer> {

    List<PlanBenefit> findByPlanId(Integer planId);

    PlanBenefit findByPlanIdAndBenefitId(Integer planId, Integer benefitId);

    Boolean existsByPlanIdAndBenefitIdAndIdNot(Integer planId, Integer benefitId, Integer id);

    Boolean existsByPlanIdAndBenefitId (Integer planId, Integer benefitId);
}
