package com.app.kinlock.common.spec;

import com.app.kinlock.common.enums.VehicleClassificationEnum;
import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.domain.entity.Regional;
import com.app.kinlock.domain.entity.VehicleCatalog;
import com.app.kinlock.presentation.dto.FilterPlanDto;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.Year;

public final class PlanSpecs {

    public static Specification<Plan> fromFilterPlanDto(FilterPlanDto f) {
        return (root, query, cb) -> {

            Join<Plan, VehicleCatalog> vehicle  = root.join("vehicleCatalog");
            Join<Plan, Regional>       regional = root.join("regional");

            Specification<Plan> ageSpec = null;
            if (f.getYear() != null) {
                int carAge = Year.now().getValue() - f.getYear();
                ageSpec = (r, q, c) -> c.lessThanOrEqualTo(
                        c.literal(carAge),
                        r.get("ageLimit"));
            }

            return SpecUtil.<Plan>compose(
                    f.getBrand() != null ?
                            SpecUtil.fieldLike(vehicle.get("brand"), f.getBrand()) : null,
                    f.getModel() != null ?
                            SpecUtil.fieldLike(vehicle.get("model"), f.getModel()) : null,
                    f.getClassification() != null ?
                            SpecUtil.fieldLike(vehicle.get("classifications"),
                                    VehicleClassificationEnum.fromString(f.getClassification()).name()) : null,
                    ageSpec,
                    f.getRegional() != null ?
                            SpecUtil.fieldLike(regional.get("name"), f.getRegional()) : null,
                    f.getLevel() != null ?
                            SpecUtil.fieldEquals(root.get("level"), f.getLevel()) : null,
                    f.getFranchise() != null ?
                            SpecUtil.fieldEquals(root.get("franchise"), f.getFranchise()) : null
            ).toPredicate(root, query, cb);
        };
    }
}