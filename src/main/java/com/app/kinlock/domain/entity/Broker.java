package com.app.kinlock.domain.entity;

import com.app.kinlock.common.enums.RoleEnum;
import com.app.kinlock.domain.service.CredentialsOwner;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Broker extends User implements CredentialsOwner {

    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role = RoleEnum.ROLE_BROKER;

    @Column(columnDefinition = "TEXT")
    private String logo;

    @ManyToMany
    @JoinTable(
            name = "broker_plan",
            joinColumns = @JoinColumn(name = "broker_id"),
            inverseJoinColumns = @JoinColumn(name = "plan_id")
    )
    private List<Plan> plans = new ArrayList<>();
}
