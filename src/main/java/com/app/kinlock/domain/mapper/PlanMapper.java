package com.app.kinlock.domain.mapper;

import com.app.kinlock.common.function.FunctionManager;
import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.presentation.dto.PlanDto;
import com.app.kinlock.presentation.pojo.PlanBenefitPojo;
import com.app.kinlock.presentation.pojo.PlanPojo;
import com.app.kinlock.utils.FunctionNames;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class PlanMapper {

    private final FunctionManager<Integer> integerFunctionManager;


    public Plan fromDto(PlanDto dto, Plan plan) {
        plan.setName(dto.getName());
        plan.setMinimumPremium(dto.getMinimumPremium());
        plan.setRate(dto.getRate());
        plan.setAgeLimit(dto.getAgeLimit());
        plan.setDiscount(dto.getDiscount());
        plan.setFranchise(dto.getFranchise());
        plan.setInterest(dto.getInterest());
        plan.setActive(dto.getState() != null ? dto.getState() : true);
        return plan;
    }

    public PlanPojo toPojo(Plan plan) {
        PlanPojo pojo = new PlanPojo();
        pojo.setId(plan.getId());
        pojo.setName(plan.getName());
        pojo.setState(plan.getActive());
        pojo.setVehicleId(plan.getVehicleCatalog().getId());
        pojo.setRegionalId(plan.getRegional().getId());
        pojo.setInsuranceId(plan.getInsurance().getId());
        pojo.setMinimumPremium(plan.getMinimumPremium());
        pojo.setRate(plan.getRate());
        pojo.setAgeLimit(plan.getAgeLimit());
        pojo.setDiscount(plan.getDiscount());
        pojo.setFranchise(plan.getFranchise());
        pojo.setInterest(plan.getInterest());
        pojo.setSegment(plan.getSegment().getName());
        pojo.setPlanType(plan.getPlanType().getName());
        pojo.setCreatedBy(plan.getBroker().getName());
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
        BigDecimal value = new BigDecimal(
                vehicleValue.replace(".", "").replace(",", "."));

        List<PlanPojo> pojos = new ArrayList<>();
        for (Plan plan : plans) {
            PlanPojo pojo = toPojo(plan);

            BigDecimal rate = BigDecimal.valueOf(plan.getRate());
            BigDecimal min = rate.multiply(value)
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

            BigDecimal minimumPremium = BigDecimal.valueOf(plan.getMinimumPremium());
            BigDecimal price = min.compareTo(minimumPremium) >= 0 ? min : minimumPremium;

            pojo.setPrice(price.setScale(0, RoundingMode.HALF_UP).doubleValue());
            pojos.add(pojo);
        }
        return pojos;
    }

    private List<PlanBenefitPojo> executeGetBenefitsByPlan(Integer id) {
        return (List<PlanBenefitPojo>) integerFunctionManager.executeAndReturn(FunctionNames.GET_ALL_BENEFITS_FROM_PLAN, id);
    }
}
