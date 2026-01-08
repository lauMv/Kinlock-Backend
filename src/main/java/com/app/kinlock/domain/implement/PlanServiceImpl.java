package com.app.kinlock.domain.implement;

import com.app.kinlock.common.function.FunctionManager;
import com.app.kinlock.common.function.NamedFunction;
import com.app.kinlock.common.security.AuthenticationFacade;
import com.app.kinlock.common.spec.PlanSpecs;
import com.app.kinlock.config.MailService;
import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.data.PlanRepository;
import com.app.kinlock.domain.entity.*;
import com.app.kinlock.domain.mapper.PlanMapper;
import com.app.kinlock.domain.service.*;
import com.app.kinlock.presentation.dto.FilterPlanDto;
import com.app.kinlock.presentation.dto.PlanDto;
import com.app.kinlock.presentation.pojo.PlanPojo;
import com.app.kinlock.utils.FunctionNames;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.domain.Specification;
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
    private final MailService mailService;
    private final AuthenticationFacade auth;
    private final BrokerAdminService brokerAdminService;
    private final FunctionManager<Integer> intFunctionManager;

    @Override
    protected GenericRepository<Plan, Integer> getRepository() {
        return planRepository;
    }

    @Override
    public PlanPojo create(PlanDto dto) {
        Plan plan = mapper.fromDto(dto, new Plan());
        setEntities(dto, plan);
        this.create(plan);
        if (auth.isBroker()) {
            Broker me = brokerAdminService.findByEmail(auth.getEmail());
            me.getPlans().add(plan);
            brokerAdminService.save(me);
        }
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

    private void setEntities(PlanDto dto, Plan plan) {
        VehicleCatalog vehicleCatalog = Optional.ofNullable(vehicleCatalogService.getById(dto.getVehicleId()))
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

    @Override
    public List<PlanPojo> search(FilterPlanDto dto) {
        Specification<Plan> spec = PlanSpecs.fromFilterPlanDto(dto);
        List<Plan> plans = planRepository.findAll(spec);
        return mapper.toListPojo(plans, dto.getVehicleValue());

    }

    @Override
    public void sendPlanToEmail(Integer id, String email) {
        PlanPojo plan = this.getPojoById(id);
//        CertificateData data = mapper.toCerti()
        mailService.sendHtml(
                email, "Informacion del plan",
                plan.toString()
        );
    }

    @EventListener(ApplicationReadyEvent.class)
    private void addFunction() {
        intFunctionManager.addFunction(new NamedFunction<>(FunctionNames.GET_PLAN_POJO, this::getPojoById));
    }

}
