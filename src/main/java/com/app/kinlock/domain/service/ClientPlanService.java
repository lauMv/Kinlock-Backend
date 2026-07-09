package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.ClientPlan;
import com.app.kinlock.presentation.dto.ClientPlanDto;

import java.util.List;

public interface ClientPlanService extends CRUDService<ClientPlan, Integer> {

    void create(ClientPlanDto dto);

    void confirmSoldPlan(Integer id);

    List<ClientPlan> getSoldPlans();

    List<ClientPlan> getOnHoldPlans();

}
