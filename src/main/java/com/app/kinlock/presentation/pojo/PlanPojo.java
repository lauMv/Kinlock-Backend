package com.app.kinlock.presentation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanPojo {

    private Integer id;
    private Boolean state;
    private Integer vehicleId;
    private String vehicleBrand;
    private String vehicleModel;
    private String regional;
    private String insurance;
    private Double minimumPremium;
    private Double rate;
    private Double discount;

}
