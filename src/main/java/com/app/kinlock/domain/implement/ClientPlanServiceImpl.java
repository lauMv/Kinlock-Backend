package com.app.kinlock.domain.implement;

import com.app.kinlock.data.ClientPlanRepository;
import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.domain.entity.ClientPlan;
import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.domain.mapper.ClientPlanMapper;
import com.app.kinlock.domain.service.ClientPlanService;
import com.app.kinlock.domain.service.PlanService;
import com.app.kinlock.presentation.dto.ClientPlanDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientPlanServiceImpl extends CRUDServiceImpl<ClientPlan, Integer> implements ClientPlanService {

    private final ClientPlanRepository repository;
    private final PlanService planService;
    private final ClientPlanMapper mapper;

    @Override
    protected GenericRepository<ClientPlan, Integer> getRepository() {
        return repository;
    }

    @Override
    public ClientPlan create(ClientPlanDto dto) {
        Plan plan = planService.getById(dto.getPlanId());
        ClientPlan clientPlan = mapper.fromDto(dto, new ClientPlan());
        clientPlan.setPlan(plan);
        return this.create(clientPlan);
    }
}
