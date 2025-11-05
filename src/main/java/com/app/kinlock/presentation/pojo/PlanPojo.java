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
    private String vehicleBrand;
    private String vehicleModel;
    private String vehicleClassification;
    private String regional;
    private String insurance;
    private Double minimumPremium;
    private Double rate;
    private Double discount;
    private Double price;
    private List<PlanBenefitPojo> benefits;

    public PlanPojo(Integer id, Boolean state, Integer vehicleId, String vehicleBrand, String vehicleModel, String regional, String insurance, Double minimumPremium, Double rate, Double discount) {
        this.id = id;
        this.state = state;
        this.vehicleId = vehicleId;
        this.vehicleBrand = vehicleBrand;
        this.vehicleModel = vehicleModel;
        this.regional = regional;
        this.insurance = insurance;
        this.minimumPremium = minimumPremium;
        this.rate = rate;
        this.discount = discount;
    }
}
