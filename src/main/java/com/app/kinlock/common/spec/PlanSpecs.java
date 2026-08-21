package com.app.kinlock.common.spec;

import com.app.kinlock.common.enums.EngineTypeEnum;
import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.domain.entity.PlanType;
import com.app.kinlock.domain.entity.Regional;
import com.app.kinlock.domain.entity.Segment;
import com.app.kinlock.domain.entity.VehicleType;
import com.app.kinlock.presentation.dto.FilterPlanDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public final class PlanSpecs {

    public static Specification<Plan> fromFilterPlanDto(FilterPlanDto f) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            // engineType is a plain enum column directly on Plan - exact match, not LIKE
            if (f.getEngineType() != null) {
                predicates.add(cb.equal(root.get("engineType"),
                        EngineTypeEnum.fromString(f.getEngineType())));
            }

            // vehicleType is a direct FK on Plan now (no more VehicleCatalog join)
            if (f.getVehicleType() != null) {
                Join<Plan, VehicleType> vehicleType = root.join("vehicleType", JoinType.LEFT);
                predicates.add(likeIgnoreCase(cb, vehicleType.get("name"), f.getVehicleType()));
            }

            if (f.getRegional() != null) {
                Join<Plan, Regional> regional = root.join("regional", JoinType.LEFT);
                predicates.add(likeIgnoreCase(cb, regional.get("name"), f.getRegional()));
            }

            if (f.getSegment() != null) {
                Join<Plan, Segment> segment = root.join("segment", JoinType.LEFT);
                predicates.add(likeIgnoreCase(cb, segment.get("name"), f.getSegment()));
            }

            if (f.getPlanType() != null) {
                Join<Plan, PlanType> planType = root.join("planType", JoinType.LEFT);
                predicates.add(likeIgnoreCase(cb, planType.get("name"), f.getPlanType()));
            }

            if (f.getFranchise() != null) {
                predicates.add(likeIgnoreCase(cb, root.get("franchise"), f.getFranchise()));
            }

            if (f.getYear() != null) {
                int carAge = Year.now().getValue() - f.getYear();
                predicates.add(cb.lessThanOrEqualTo(cb.literal(carAge), root.get("ageLimit")));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static Predicate likeIgnoreCase(CriteriaBuilder cb, Path<String> path, String value) {
        return cb.like(cb.lower(path), "%" + value.trim().toLowerCase() + "%");
    }
}