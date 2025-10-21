package com.app.kinlock.domain.mapper;

import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.domain.entity.VehicleCatalog;
import com.app.kinlock.presentation.dto.PlanDto;
import com.app.kinlock.presentation.pojo.PlanPojo;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {

    public Plan fromDto (PlanDto dto, Plan plan){
        plan.setMinimumPremium(dto.getMinimumPremium());
        plan.setRate(dto.getRate());
        plan.setAgeLimit(dto.getAgeLimit());
        plan.setDiscount(dto.getDiscount());
        return plan;
    }

    public PlanPojo toPojo(Plan plan){
        PlanPojo pojo = new PlanPojo();
        pojo.setId(plan.getId());
        pojo.setVehicleId(plan.getVehicleCatalog().getId());
        pojo.setVehicleBrand(plan.getVehicleCatalog().getBrand());
        pojo.setVehicleModel(plan.getVehicleCatalog().getModel());
        pojo.setRegional(plan.getRegional().getName());
        pojo.setInsurance(plan.getInsurance().getName());
        pojo.setMinimumPremium(plan.getMinimumPremium());
        pojo.setRate(plan.getRate());
        pojo.setDiscount(plan.getDiscount());
        return pojo;
    }
}
