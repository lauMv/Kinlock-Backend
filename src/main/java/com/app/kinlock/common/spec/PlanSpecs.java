package com.app.kinlock.common.spec;

import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.domain.entity.Regional;
import com.app.kinlock.domain.entity.VehicleCatalog;
import com.app.kinlock.presentation.dto.FilterPlanDto;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class PlanSpecs {

    public static Specification<Plan> fromFilterPlanDto(FilterPlanDto f) {
        return (root, query, cb) -> {

            Join<Plan, VehicleCatalog> vehicle  = root.join("vehicleCatalog");
            Join<Plan, Regional>       regional = root.join("regional");

            Specification<Plan> spec = SpecUtil.compose(
                    SpecUtil.fieldLike(String.valueOf(vehicle.get("brand")),        f.getBrand()),
                    SpecUtil.fieldLike(String.valueOf(vehicle.get("model")),        f.getModel()),
                    SpecUtil.fieldLike(String.valueOf(vehicle.get("classification")), f.getClassification()),
                    SpecUtil.fieldEquals(String.valueOf(root.get("ageLimit")),       f.getYear()),
                    SpecUtil.fieldEquals(String.valueOf(regional.get("name")),      f.getRegional()),
                    SpecUtil.fieldEquals(String.valueOf(root.get("level")),         f.getLevel()),
                    SpecUtil.fieldEquals(String.valueOf(root.get("franchise")),     f.getFranchise()),

                    f.getYear() != null
                            ? SpecUtil.fieldLessThanEqual(
                            String.valueOf(root.get("ageLimit")),
                            Double.parseDouble(f.getYear().toString()))
                            : null
            );
            return spec.toPredicate(root, query, cb);
        };
    }
}