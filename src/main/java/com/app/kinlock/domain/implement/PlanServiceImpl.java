package com.app.kinlock.domain.implement;

import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.data.PlanRepository;
import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.domain.entity.Vehicle;
import com.app.kinlock.domain.mapper.PlanMapper;
import com.app.kinlock.domain.service.PlanService;
import com.app.kinlock.domain.service.VehicleService;
import com.app.kinlock.presentation.dto.PlanDto;
import com.app.kinlock.presentation.pojo.PlanPojo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlanServiceImpl extends CRUDServiceImpl<Plan, Integer> implements PlanService {

    private final PlanRepository planRepository;
    private final VehicleService vehicleService;
    private final PlanMapper mapper;

    @Override
    protected GenericRepository<Plan, Integer> getRepository() {
        return planRepository;
    }

    @Override
    public PlanPojo create(PlanDto dto) {
        Vehicle vehicle = vehicleService.getById(dto.getVehicleId());
        Plan plan = mapper.fromDto(dto, new Plan(), vehicle);
        this.create(plan);
        return mapper.toPojo(plan);
    }

    @Override
    public PlanPojo update(Integer id, PlanDto dto) {
        Vehicle vehicle = vehicleService.getById(dto.getVehicleId());
        Plan plan = this.getById(id);
        plan = mapper.fromDto(dto, plan, vehicle);
        this.create(plan);
        return mapper.toPojo(plan);
    }

    @Override
    public PlanPojo getPojoById(Integer id) {
        Plan plan = this.getById(id);
        return mapper.toPojo(plan);
    }

    @Override
    public List<PlanPojo> getAllPojo() {
        return planRepository.getAllPojo();
    }
}
