package com.app.kinlock.domain.mapper;

import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.domain.entity.VehicleCatalog;
import com.app.kinlock.presentation.dto.PlanDto;
import com.app.kinlock.presentation.pojo.PlanPojo;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {

    public Plan fromDto (PlanDto dto, Plan plan, VehicleCatalog vehicleCatalog){
        plan.setVehicleCatalog(vehicleCatalog);
        plan.setDiscount(dto.getDiscount());
        plan.setTotal(dto.getTotal());
        return plan;
    }

    public PlanPojo toPojo(Plan plan){
        PlanPojo pojo = new PlanPojo();
        pojo.setId(plan.getId());
        pojo.setVehicleId(plan.getVehicleCatalog().getId());
        pojo.setVehicleBrand(plan.getVehicleCatalog().getBrand());
        pojo.setVehicleModel(plan.getVehicleCatalog().getModel());
        pojo.setDiscount(plan.getDiscount());
        pojo.setTotal(plan.getTotal());
        return pojo;
    }
}
