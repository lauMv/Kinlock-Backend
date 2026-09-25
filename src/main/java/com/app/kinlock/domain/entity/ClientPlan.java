package com.app.kinlock.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class ClientPlan extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;
    private String vehicleBrand;
    private String vehicleModel;
    private Double vehiclePrice;
    private String vehiclePlate;

    @Column(columnDefinition = "TEXT")
    private String vehiclePicRuat;
    @Column(columnDefinition = "TEXT")
    private String vehiclePicFront;
    @Column(columnDefinition = "TEXT")
    private String vehiclePicBack;
    @Column(columnDefinition = "TEXT")
    private String vehiclePicRight;
    @Column(columnDefinition = "TEXT")
    private String vehiclePicLeft;
    @Column(columnDefinition = "TEXT")
    private String vehiclePicChasis;
    @Column(columnDefinition = "TEXT")
    private String vehiclePicMileage;

    private Boolean soldConfirmation = Boolean.FALSE;
}
