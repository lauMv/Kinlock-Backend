package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.PlanType;
import com.app.kinlock.presentation.dto.PlanTypeDto;

public interface PlanTypeService extends CRUDService<PlanType, Integer> {

    PlanType create(PlanTypeDto dto);

    PlanType update(Integer id, PlanTypeDto dto);

    PlanType getByName(String name);

}