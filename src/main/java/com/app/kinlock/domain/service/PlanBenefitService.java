package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.PlanBenefit;
import com.app.kinlock.presentation.dto.PlanBenefitDto;
import com.app.kinlock.presentation.pojo.PlanBenefitPojo;

import java.util.List;

public interface PlanBenefitService extends CRUDService<PlanBenefit, Integer> {

    List<PlanBenefitPojo> getAllByPlan(Integer planId);

    PlanBenefitPojo getPojoById(Integer id);

    PlanBenefitPojo create(PlanBenefitDto dto);

    PlanBenefitPojo update(Integer id, PlanBenefitDto dto);
}
