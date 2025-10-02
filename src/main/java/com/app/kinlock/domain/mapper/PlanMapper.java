package com.app.kinlock.domain.mapper;

import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.domain.entity.Vehicle;
import com.app.kinlock.presentation.dto.PlanDto;
import com.app.kinlock.presentation.pojo.PlanPojo;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {

    public Plan fromDto (PlanDto dto, Plan plan, Vehicle vehicle){
        plan.setVehicle(vehicle);
        plan.setDiscount(dto.getDiscount());
        plan.setTotal(dto.getTotal());
        return plan;
    }

    public PlanPojo toPojo(Plan plan){
        PlanPojo pojo = new PlanPojo();
        pojo.setId(plan.getId());
        pojo.setVehicleId(plan.getVehicle().getId());
        pojo.setVehicleName(plan.getVehicle().getName());
        pojo.setDiscount(plan.getDiscount());
        pojo.setTotal(plan.getTotal());
        return pojo;
    }
}
