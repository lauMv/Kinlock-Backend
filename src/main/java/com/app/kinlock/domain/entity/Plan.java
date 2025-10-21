package com.app.kinlock.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "vehicle_catalog_id")
    private VehicleCatalog vehicleCatalog;

    @ManyToOne
    @JoinColumn(name = "regional_id")
    private Regional regional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_id", nullable = false)
    private Insurance insurance;

    private Double minimumPremium;
    private Double rate;
    private Integer ageLimit;

    private Double discount;

    @OneToMany(mappedBy = "plan", cascade = ALL, orphanRemoval = true)
    private List<PlanBenefit> planBenefits = new ArrayList<>();
}
