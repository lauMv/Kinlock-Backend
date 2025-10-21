package com.app.kinlock.presentation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanPojo {

    private Integer id;
    private Integer vehicleId;
    private String vehicleBrand;
    private String vehicleModel;
    private double discount;
    private double total;

}
