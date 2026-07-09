package com.app.kinlock.domain.implement;

import com.app.kinlock.data.ClientPlanRepository;
import com.app.kinlock.data.ClientRepository;
import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.domain.entity.Client;
import com.app.kinlock.domain.entity.ClientPlan;
import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.domain.mapper.ClientMapper;
import com.app.kinlock.domain.mapper.ClientPlanMapper;
import com.app.kinlock.domain.service.ClientPlanService;
import com.app.kinlock.domain.service.PlanService;
import com.app.kinlock.presentation.dto.ClientPlanDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientPlanServiceImpl extends CRUDServiceImpl<ClientPlan, Integer> implements ClientPlanService {

    private final ClientPlanRepository repository;
    private final PlanService planService;
    private final ClientPlanMapper mapper;
    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    @Override
    protected GenericRepository<ClientPlan, Integer> getRepository() {
        return repository;
    }

    @Override
    public void create(ClientPlanDto dto) {
        Plan plan = planService.getById(dto.getPlanId());
        Client client = clientMapper.fromClientPlanDto(dto);
        clientRepository.save(client);
        ClientPlan clientPlan = mapper.fromDto(dto, new ClientPlan(), client);
        clientPlan.setPlan(plan);
        this.create(clientPlan);
    }

    public void confirmSoldPlan(Integer id){
        ClientPlan plan = this.getById(id);
        plan.setSoldConfirmation(Boolean.TRUE);
        repository.save(plan);
    }

    @Override
    public List<ClientPlan> getSoldPlans() {
        return repository.findAllBySoldConfirmationIsTrue();
    }

    @Override
    public List<ClientPlan> getOnHoldPlans() {
        return repository.findAllBySoldConfirmationIsFalse();
    }

}
