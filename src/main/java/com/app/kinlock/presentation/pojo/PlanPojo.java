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
    private Boolean state;
    private Integer vehicleId;
    private Integer regionalId;
    private Integer insuranceId;
    private Double minimumPremium;
    private Double rate;
    private Integer ageLimit;
    private Double discount;
    private Double price;
    private String level;
    private String franchise;
    private List<PlanBenefitPojo> benefits;

    public PlanPojo(Integer id, Boolean state, Integer vehicleId, Integer regionalId, Integer insuranceId, Double minimumPremium, Double rate, Integer ageLimit, Double discount, String level, String franchise) {
        this.id = id;
        this.state = state;
        this.vehicleId = vehicleId;
        this.regionalId = regionalId;
        this.insuranceId = insuranceId;
        this.minimumPremium = minimumPremium;
        this.rate = rate;
        this.ageLimit = ageLimit;
        this.discount = discount;
        this.level = level;
        this.franchise = franchise;
    }
}
