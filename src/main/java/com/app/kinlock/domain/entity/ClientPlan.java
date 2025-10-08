package com.app.kinlock.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ClientPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;
    private Double vehiclePrice;
    private String vehiclePlate;
    private String vehicleColor;
}
