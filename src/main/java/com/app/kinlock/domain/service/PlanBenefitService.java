package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.PlanBenefit;
import com.app.kinlock.presentation.dto.PlanBenefitDto;

import java.util.List;
import java.util.Map;

public interface PlanBenefitService extends CRUDService<PlanBenefit, Integer> {

    List<PlanBenefit> getAllByPlan(Integer planId);

    PlanBenefit create(PlanBenefitDto dto);

    PlanBenefit update(Integer id, PlanBenefitDto dto);

    void delete(Integer planId, Integer benefitCatalogId);

    Map<String, Double> parseLimits(String json);

}
