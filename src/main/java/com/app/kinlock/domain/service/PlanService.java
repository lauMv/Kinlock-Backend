package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.presentation.dto.FilterPlanDto;
import com.app.kinlock.presentation.dto.PlanDto;
import com.app.kinlock.presentation.pojo.PlanPojo;

import java.util.List;

public interface PlanService extends CRUDService<Plan, Integer> {

    PlanPojo create(PlanDto dto);

    PlanPojo update(Integer id, PlanDto dto);

    void save(Plan plan);

    PlanPojo getPojoById(Integer id);

    List<PlanPojo> getAllPojo();

    List<PlanPojo> search(FilterPlanDto dto);

    void sendPlanToEmail(Integer id, String email);
}
