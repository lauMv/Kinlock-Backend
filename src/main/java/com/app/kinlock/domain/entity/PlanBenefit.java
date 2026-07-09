package com.app.kinlock.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "plan_benefit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanBenefit extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "benefit_id")
    private Benefit benefit;

    private String description;

}
