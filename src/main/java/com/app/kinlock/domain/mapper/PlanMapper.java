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
        plan.setActive(dto.getState() != null ? dto.getState() : true);
        plan.setLevel(dto.getLevel() != null ? dto.getLevel() : "basic");
        plan.setFranchise(dto.getFranchise() != null ? dto.getFranchise() : "1000");
        return plan;
    }

    public PlanPojo toPojo(Plan plan) {
        PlanPojo pojo = new PlanPojo();
        pojo.setId(plan.getId());
        pojo.setState(plan.getActive());
        pojo.setVehicleId(plan.getVehicleCatalog().getId());
        pojo.setRegionalId(plan.getRegional().getId());
        pojo.setInsuranceId(plan.getInsurance().getId());
        pojo.setMinimumPremium(plan.getMinimumPremium());
        pojo.setRate(plan.getRate());
        pojo.setAgeLimit(plan.getAgeLimit());
        pojo.setDiscount(plan.getDiscount());
        pojo.setLevel(plan.getLevel());
        pojo.setLevel(plan.getLevel());
        pojo.setBenefits(executeGetBenefitsByPlan(plan.getId()));
        return pojo;
    }

//    CertificateData toCertificateData(Plan plan){
//        CertificateData data = new CertificateData();
//        data.setNombreCliente(plan);
//    }

    public List<PlanPojo> toListPojo(List<Plan> plans) {
        List<PlanPojo> pojos = new ArrayList<>();
        for (Plan plan : plans) {
            PlanPojo pojo = toPojo(plan);
            pojos.add(pojo);
        }
        return pojos;
    }

    public List<PlanPojo> toListPojo(List<Plan> plans, String vehicleValue) {
        double value = Double.parseDouble(
                vehicleValue.replace(".", "").replace(",", "."));

        List<PlanPojo> pojos = new ArrayList<>();
        for (Plan plan : plans) {
            PlanPojo pojo = toPojo(plan);
            double min = (plan.getRate() * value) / 100;
            pojo.setPrice(min >= plan.getMinimumPremium() ? min : plan.getMinimumPremium());
            pojos.add(pojo);
        }
        return pojos;
    }

    private List<PlanBenefitPojo> executeGetBenefitsByPlan(Integer id) {
        return (List<PlanBenefitPojo>) integerFunctionManager.executeAndReturn(FunctionNames.GET_ALL_BENEFITS_FROM_PLAN, id);
    }
}
