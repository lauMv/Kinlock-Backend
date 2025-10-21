package com.app.kinlock.domain.implement;

import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.data.PlanBenefitRepository;
import com.app.kinlock.domain.entity.Benefit;
import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.domain.entity.PlanBenefit;
import com.app.kinlock.domain.service.BenefitService;
import com.app.kinlock.domain.service.PlanBenefitService;
import com.app.kinlock.domain.service.PlanService;
import com.app.kinlock.exceptions.DuplicatedException;
import com.app.kinlock.presentation.dto.PlanBenefitDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@AllArgsConstructor
public class PlanBenefitServiceImpl extends CRUDServiceImpl<PlanBenefit, Integer> implements PlanBenefitService {

    private final PlanBenefitRepository repository;
    private final ObjectMapper mapper = new ObjectMapper();
    private final PlanService planService;
    private final BenefitService benefitService;


    @Override
    protected GenericRepository<PlanBenefit, Integer> getRepository() {
        return repository;
    }

    @Override
    public List<PlanBenefit> getAllByPlan(Integer planId) {
        return repository.findByPlanId(planId);
    }

    @Override
    public PlanBenefit create(PlanBenefitDto dto) {
        Plan plan = Optional.ofNullable(planService.getById(dto.getPlanId()))
                .orElseThrow(() -> new IllegalArgumentException("Plan no encontrado"));

        Benefit benefit = Optional.ofNullable(benefitService.getById(dto.getBenefitId()))
                .orElseThrow(() -> new IllegalArgumentException("Beneficio no encontrado"));
        PlanBenefit planBenefit = new PlanBenefit();
        planBenefit.setPlan(plan);
        planBenefit.setBenefit(benefit);
        planBenefit.setLimits(dto.getLimits());
        return this.create(planBenefit);
    }

    @Override
    public PlanBenefit update(Integer id, PlanBenefitDto dto) {
        PlanBenefit planBenefit = this.getById(id);
        Plan plan = Optional.ofNullable(planService.getById(dto.getPlanId()))
                .orElseThrow(() -> new IllegalArgumentException("Plan no encontrado"));

        Benefit benefit = Optional.ofNullable(benefitService.getById(dto.getBenefitId()))
                .orElseThrow(() -> new IllegalArgumentException("Beneficio no encontrado"));
        if (repository.existsByPlanIdAndBenefitIdAndIdNot(dto.getPlanId(), dto.getBenefitId(), id)){
            throw new DuplicatedException("Los beneficios del plan ya existen");
        }
        planBenefit.setPlan(plan);
        planBenefit.setBenefit(benefit);
        planBenefit.setLimits(dto.getLimits());
        return this.create(planBenefit);
    }

    @Override
    public void delete(Integer planId, Integer benefitId) {
        PlanBenefit planBenefit = repository.findByPlanIdAndBenefitId(planId, benefitId);
        this.delete(planBenefit.getId());
    }

    @Override
    public Map<String, Double> parseLimits(String json) {
        try {
            return mapper.readValue(json, new TypeReference<>() {});
        } catch (Exception e) {
            throw new IllegalArgumentException("JSON de limites invalidos", e);
        }    }
}
