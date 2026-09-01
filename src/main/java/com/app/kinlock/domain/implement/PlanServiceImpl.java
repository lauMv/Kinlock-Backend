package com.app.kinlock.domain.implement;

import com.app.kinlock.common.enums.EngineTypeEnum;
import com.app.kinlock.common.security.AuthenticationFacade;
import com.app.kinlock.common.spec.PlanSpecs;
import com.app.kinlock.config.MailService;
import com.app.kinlock.data.BrokerRepository;
import com.app.kinlock.data.ClientRepository;
import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.data.PlanRepository;
import com.app.kinlock.domain.entity.*;
import com.app.kinlock.domain.events.PlanCreatedEvent;
import com.app.kinlock.domain.mapper.PlanMapper;
import com.app.kinlock.domain.service.*;
import com.app.kinlock.exceptions.EntityNotFoundException;
import com.app.kinlock.exceptions.MandatoryFieldException;
import com.app.kinlock.presentation.dto.FilterPlanDto;
import com.app.kinlock.presentation.dto.PlanDto;
import com.app.kinlock.presentation.dto.SendEmailDto;
import com.app.kinlock.presentation.pojo.PlanPojo;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlanServiceImpl extends CRUDServiceImpl<Plan, Integer> implements PlanService {

    private final PlanRepository planRepository;
    private final BrokerRepository brokerRepository;
    private final VehicleCatalogService vehicleCatalogService;
    private final VehicleTypeService vehicleTypeService;
    private final RegionalService regionalService;
    private final InsuranceService insuranceService;
    private final PlanTypeService planTypeService;
    private final SegmentService segmentService;
    private final PlanMapper mapper;
    private final MailService mailService;
    private final AuthenticationFacade auth;
    private final ClientRepository clientRepository;
    private final ApplicationEventPublisher eventPublisher;

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
            eventPublisher.publishEvent(new PlanCreatedEvent(plan, auth.getEmail()));
        } else if (auth.isAdmin() && dto.getBrokerId() != null) {
            eventPublisher.publishEvent(new PlanCreatedEvent(plan, null, dto.getBrokerId()));
        }
        return mapper.toPojo(plan);
    }

    @Override
    public PlanPojo update(Integer id, PlanDto dto) {
        Plan plan = this.getById(id);
        plan = mapper.fromDto(dto, plan);
        setEntities(dto, plan);
        Broker broker = dto.getBrokerId() != null
                ? brokerRepository.findById(dto.getBrokerId())
                        .orElseThrow(() -> new IllegalArgumentException("Broker no encontrado"))
                : null;
        plan.setBroker(broker);
        this.create(plan);
        return mapper.toPojo(plan);
    }

    @Override
    public void save(Plan plan) {
        planRepository.save(plan);
    }

    private void setEntities(PlanDto dto, Plan plan) {
        Regional regional = Optional.ofNullable(regionalService.getById(dto.getRegionalId()))
                .orElseThrow(() -> new EntityNotFoundException("Regional no encontrada"));
        plan.setRegional(regional);
        Insurance insurance = Optional.ofNullable(insuranceService.getById(dto.getInsuranceId()))
                .orElseThrow(() -> new EntityNotFoundException("Seguro no encontrado"));
        plan.setInsurance(insurance);
        Segment segment = Optional.ofNullable(segmentService.getById(dto.getSegmentId()))
                .orElseThrow(()-> new EntityNotFoundException("Segmento no encontrado"));
        plan.setSegment(segment);
        EngineTypeEnum engineType = EngineTypeEnum.fromString(dto.getEngineType());
        plan.setEngineType(engineType);
        VehicleType vehicleType = Optional.ofNullable(vehicleTypeService.getById(dto.getVehicleTypeId()))
                .orElseThrow(() -> new EntityNotFoundException("Tipo de vehiculo no encontrado"));
        plan.setVehicleType(vehicleType);
        PlanType planType = Optional.ofNullable(planTypeService.getById(dto.getPlanTypeId()))
                .orElseThrow(()-> new EntityNotFoundException("Tipo de plan no encontrado"));
        plan.setPlanType(planType);
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
        if (dto.getClientName() != null && dto.getClientEmail() != null && dto.getClientPhone() != null){
            Client newClient = new Client(dto.getClientName(), dto.getClientEmail(), dto.getClientPhone());
            clientRepository.save(newClient);
        }
        if (StringUtils.isBlank(dto.getBrand()) || StringUtils.isBlank(dto.getModel())) {
            throw new MandatoryFieldException("marca", "modelo");
        }
        VehicleCatalog vehicle = vehicleCatalogService
                .getByBrandAndModel(dto.getBrand().trim(), dto.getModel().trim());
        dto.setVehicleType(vehicle.getVehicleType().getName());
        dto.setClassification(vehicle.getClassification().getValue());
        dto.setEngineType(vehicle.getEngineType().getValue());
        dto.setSegment(vehicle.getSegment().getName());
        Specification<Plan> spec = PlanSpecs.fromFilterPlanDto(dto);
        List<Plan> plans = planRepository.findAll(spec);
        return mapper.toListPojo(plans, dto.getVehicleValue());

    }

    @Override
    public void sendPlanToEmail(SendEmailDto dto) {
        byte[] pdfBytes = Base64.getDecoder().decode(dto.getBase64Pdf());
        mailService.sendWithAttachment(
                dto.getEmail(),
                "Plan",
                "Adjunto encontraras tu plan.",
                pdfBytes,
                "plan_info.pdf",
                "application/pdf"
        );
    }


}
