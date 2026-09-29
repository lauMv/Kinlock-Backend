package com.app.kinlock.presentation.pojo;

import com.app.kinlock.domain.entity.Client;
import com.app.kinlock.domain.entity.VehicleCatalog;
import lombok.Data;

@Data
public class ClientPlanPojo {

    private Integer id;
    private PlanPojo plan;
    private Client client;
    private String vehicleBrand;
    private String vehicleModel;
    private Double vehiclePrice;
    private String vehiclePlate;
    private String vehiclePicRuat;
    private String vehiclePicFront;
    private String vehiclePicBack;
    private String vehiclePicRight;
    private String vehiclePicLeft;
    private String vehiclePicChasis;
    private String vehiclePicMileage;
    private Boolean soldConfirmation;
}