package com.app.kinlock.presentation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanPojo {

    private Integer id;
    private String name;
    private Boolean state;
    private Integer vehicleId;
    private Integer regionalId;
    private Integer insuranceId;
    private Double minimumPremium;
    private Double rate;
    private Integer ageLimit;
    private Double discount;
    private Double price;
    private String franchise;
    private Double interest;
    private String segment;
    private String planType;
    private Integer brokerId;
    private String createdBy;
    private List<PlanBenefitPojo> benefits;

    public PlanPojo(Integer id, String name, Boolean state, Integer vehicleId, Integer regionalId, Integer insuranceId, Double minimumPremium, Double rate, Integer ageLimit, Double discount, String franchise, Double interest, String segment, String planType, Integer brokerId, String createdBy) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.vehicleId = vehicleId;
        this.regionalId = regionalId;
        this.insuranceId = insuranceId;
        this.minimumPremium = minimumPremium;
        this.rate = rate;
        this.ageLimit = ageLimit;
        this.discount = discount;
        this.franchise = franchise;
        this.interest = interest;
        this.segment = segment;
        this.planType = planType;
        this.brokerId = brokerId;
        this.createdBy = createdBy;
    }
}
