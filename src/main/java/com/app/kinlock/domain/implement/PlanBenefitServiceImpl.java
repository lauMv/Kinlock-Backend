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
import com.app.kinlock.exceptions.EntityNotFoundException;
import com.app.kinlock.presentation.dto.PlanBenefitDto;
import com.app.kinlock.presentation.pojo.LimitPojo;
import com.app.kinlock.presentation.pojo.PlanBenefitPojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


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
    public List<PlanBenefitPojo> getAllByPlan(Integer planId) {
        List<PlanBenefit> all = repository.findByPlanId(planId);
        List<PlanBenefitPojo> pojos = new ArrayList<>();
        for (PlanBenefit benefit : all) {
            PlanBenefitPojo pojo = toPojo(benefit);
            pojos.add(pojo);
        }
        return pojos;
    }

    @Override
    public PlanBenefitPojo getPojoById(Integer id) {
        PlanBenefit benefit = this.getById(id);
        return toPojo(benefit);
    }

    @Override
    public PlanBenefitPojo create(PlanBenefitDto dto) {
        if (repository.existsByPlanIdAndBenefitId(dto.getPlanId(), dto.getBenefitId())) {
            throw new DuplicatedException("El beneficio ya fue agregado al plan");
        }
        Plan plan = Optional.ofNullable(planService.getById(dto.getPlanId()))
                .orElseThrow(() -> new EntityNotFoundException("Plan", dto.getPlanId()));

        Benefit benefit = Optional.ofNullable(benefitService.getById(dto.getBenefitId()))
                .orElseThrow(() -> new EntityNotFoundException("Beneficio", dto.getBenefitId()));
        PlanBenefit planBenefit = new PlanBenefit();
        planBenefit.setPlan(plan);
        planBenefit.setBenefit(benefit);
        planBenefit.setLimits(toMap(dto.getLimits()));
        return toPojo(this.create(planBenefit));
    }

    @Override
    public PlanBenefitPojo update(Integer id, PlanBenefitDto dto) {
        PlanBenefit planBenefit = this.getById(id);
        Plan plan = Optional.ofNullable(planService.getById(dto.getPlanId()))
                .orElseThrow(() -> new IllegalArgumentException("Plan no encontrado"));

        Benefit benefit = Optional.ofNullable(benefitService.getById(dto.getBenefitId()))
                .orElseThrow(() -> new IllegalArgumentException("Beneficio no encontrado"));
        if (repository.existsByPlanIdAndBenefitIdAndIdNot(dto.getPlanId(), dto.getBenefitId(), id)) {
            throw new DuplicatedException("Los beneficios del plan ya existen");
        }
        planBenefit.setPlan(plan);
        planBenefit.setBenefit(benefit);
        planBenefit.setLimits(toMap(dto.getLimits()));
        return toPojo(this.create(planBenefit));
    }

    private PlanBenefitPojo toPojo(PlanBenefit benefit) {
        PlanBenefitPojo pojo = new PlanBenefitPojo();
        pojo.setId(benefit.getId());
        pojo.setPlanId(benefit.getPlan().getId());
        pojo.setBenefitId(benefit.getBenefit().getId());
        pojo.setBenefitName(benefit.getBenefit().getName());
        List<LimitPojo> list = benefit.getLimits().entrySet().stream()
                .map(e -> new LimitPojo(e.getKey(), e.getValue()))
                .toList();
        pojo.setLimits(list);
        return pojo;
    }

    private Map<String, Double> toMap(List<LimitPojo> list) {
        if (list == null) return Map.of();
        return list.stream()
                .collect(Collectors.toMap(
                        LimitPojo::getName,
                        LimitPojo::getLimit,
                        (v1, v2) -> v2));
    }
}
