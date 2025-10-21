package com.app.kinlock.domain.implement;

import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.data.PlanRepository;
import com.app.kinlock.domain.entity.Insurance;
import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.domain.entity.Regional;
import com.app.kinlock.domain.entity.VehicleCatalog;
import com.app.kinlock.domain.mapper.PlanMapper;
import com.app.kinlock.domain.service.InsuranceService;
import com.app.kinlock.domain.service.PlanService;
import com.app.kinlock.domain.service.RegionalService;
import com.app.kinlock.domain.service.VehicleCatalogService;
import com.app.kinlock.presentation.dto.PlanDto;
import com.app.kinlock.presentation.pojo.PlanPojo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlanServiceImpl extends CRUDServiceImpl<Plan, Integer> implements PlanService {

    private final PlanRepository planRepository;
    private final VehicleCatalogService vehicleCatalogService;
    private final RegionalService regionalService;
    private final InsuranceService insuranceService;
    private final PlanMapper mapper;

    @Override
    protected GenericRepository<Plan, Integer> getRepository() {
        return planRepository;
    }

    @Override
    public PlanPojo create(PlanDto dto) {
        Plan plan = mapper.fromDto(dto, new Plan());
        setEntities(dto, plan);
        this.create(plan);
        return mapper.toPojo(plan);
    }

    @Override
    public PlanPojo update(Integer id, PlanDto dto) {
        Plan plan = this.getById(id);
        plan = mapper.fromDto(dto, plan);
        setEntities(dto, plan);
        this.create(plan);
        return mapper.toPojo(plan);
    }

    private void setEntities(PlanDto dto, Plan plan){
        VehicleCatalog vehicleCatalog = Optional.ofNullable(vehicleCatalogService.getById(dto.getVehicleCatalogId()))
                .orElseThrow(() -> new IllegalArgumentException("Vehiculo no encontrado"));
        plan.setVehicleCatalog(vehicleCatalog);
        Regional regional = Optional.ofNullable(regionalService.getById(dto.getRegionalId()))
                .orElseThrow(() -> new IllegalArgumentException("Regional no encontrada"));
        plan.setRegional(regional);
        Insurance insurance = Optional.ofNullable(insuranceService.getById(dto.getInsuranceId()))
                .orElseThrow(() -> new IllegalArgumentException("Seguro no encontrado"));
        plan.setInsurance(insurance);
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
