package com.app.kinlock.domain.entity;

import com.app.kinlock.common.enums.BenefitsCoverageEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "benefits")
public class Benefit extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private BenefitsCoverageEnum coverage;
}
