package com.app.kinlock.domain.entity;

import com.app.kinlock.common.enums.BenefitsCoverageEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "benefits")
@AllArgsConstructor
@NoArgsConstructor
public class Benefit extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private BenefitsCoverageEnum coverage;

    public Benefit(String name, String description, BenefitsCoverageEnum coverage) {
        this.name = name;
        this.description = description;
        this.coverage = coverage;
    }
}
