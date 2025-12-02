package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.ClientPlan;
import com.app.kinlock.presentation.dto.ClientPlanDto;

public interface ClientPlanService extends CRUDService<ClientPlan, Integer> {

    ClientPlan create(ClientPlanDto dto);

}
