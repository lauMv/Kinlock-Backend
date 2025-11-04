package com.app.kinlock.domain.mapper;

import com.app.kinlock.common.function.FunctionManager;
import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.presentation.dto.PlanDto;
import com.app.kinlock.presentation.pojo.PlanBenefitPojo;
import com.app.kinlock.presentation.pojo.PlanPojo;
import com.app.kinlock.utils.FunctionNames;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class PlanMapper {

    private final FunctionManager<Integer> integerFunctionManager;


    public Plan fromDto(PlanDto dto, Plan plan) {
        plan.setMinimumPremium(dto.getMinimumPremium());
        plan.setRate(dto.getRate());
        plan.setAgeLimit(dto.getAgeLimit());
        plan.setDiscount(dto.getDiscount());
        plan.setActive(dto.getState());
        return plan;
    }

    public PlanPojo toPojo(Plan plan) {
        PlanPojo pojo = new PlanPojo();
        pojo.setId(plan.getId());
        pojo.setState(plan.getActive());
        pojo.setVehicleId(plan.getVehicleCatalog().getId());
        pojo.setVehicleBrand(plan.getVehicleCatalog().getBrand());
        pojo.setVehicleModel(plan.getVehicleCatalog().getModel());
        pojo.setRegional(plan.getRegional().getName());
        pojo.setInsurance(plan.getInsurance().getName());
        pojo.setMinimumPremium(plan.getMinimumPremium());
        pojo.setRate(plan.getRate());
        pojo.setDiscount(plan.getDiscount());
        pojo.setBenefits(executeGetBenefitsByPlan(plan.getId()));
        return pojo;
    }

    public List<PlanPojo> toListPojo(List<Plan> plans) {
        List<PlanPojo> pojos = new ArrayList<>();
        for (Plan plan : plans) {
            PlanPojo pojo = toPojo(plan);
            pojos.add(pojo);
        }
        return pojos;
    }

    private List<PlanBenefitPojo> executeGetBenefitsByPlan(Integer id) {
        return (List<PlanBenefitPojo>) integerFunctionManager.executeAndReturn(FunctionNames.GET_ALL_BENEFITS_FROM_PLAN, id);
    }
}
