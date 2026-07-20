package com.app.kinlock.common.spec;

import com.app.kinlock.common.enums.EngineTypeEnum;
import com.app.kinlock.common.enums.VehicleClassificationEnum;
import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.domain.entity.PlanType;
import com.app.kinlock.domain.entity.Regional;
import com.app.kinlock.domain.entity.Segment;
import com.app.kinlock.domain.entity.VehicleCatalog;
import com.app.kinlock.domain.entity.VehicleType;
import com.app.kinlock.presentation.dto.FilterPlanDto;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.Year;

public final class PlanSpecs {

    public static Specification<Plan> fromFilterPlanDto(FilterPlanDto f) {
        return (root, query, cb) -> {

            Join<Plan, VehicleCatalog> vehicle     = root.join("vehicleCatalog");
            Join<VehicleCatalog, VehicleType> vehicleType = vehicle.join("vehicleType");
            Join<Plan, Regional>       regional    = root.join("regional");
            Join<Plan, Segment>        segment     = root.join("segment");
            Join<Plan, PlanType>       planType    = root.join("planType");

            Specification<Plan> ageSpec = null;
            if (f.getYear() != null) {
                int carAge = Year.now().getValue() - f.getYear();
                ageSpec = (r, q, c) -> c.lessThanOrEqualTo(
                        c.literal(carAge),
                        r.get("ageLimit"));
            }

            return SpecUtil.<Plan>compose(
                    f.getVehicleType() != null ?
                            SpecUtil.fieldLike(vehicleType.get("name"), f.getVehicleType()) : null,
                    f.getBrand() != null ?
                            SpecUtil.fieldLike(vehicle.get("brand"), f.getBrand()) : null,
                    f.getClassification() != null ?
                            SpecUtil.fieldLike(vehicle.get("classification"),
                                    VehicleClassificationEnum.fromString(f.getClassification()).name()) : null,
                    f.getEngineType() != null ?
                            SpecUtil.fieldLike(vehicle.get("engineType"),
                                    EngineTypeEnum.fromString(f.getEngineType()).name()) : null,
                    ageSpec,
                    f.getRegional() != null ?
                            SpecUtil.fieldLike(regional.get("name"), f.getRegional()) : null,
                    f.getFranchise() != null ?
                            SpecUtil.fieldLike(root.get("franchise"), f.getFranchise()) : null,
                    f.getSegment() != null ?
                            SpecUtil.fieldLike(segment.get("name"), f.getSegment()) : null,
                    f.getPlanType() != null ?
                            SpecUtil.fieldLike(planType.get("name"), f.getPlanType()) : null
            ).toPredicate(root, query, cb);
        };
    }
}