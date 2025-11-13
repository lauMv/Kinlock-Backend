package com.app.kinlock.domain.entity;

import com.app.kinlock.common.enums.RoleEnum;
import com.app.kinlock.domain.service.CredentialsOwner;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Admin extends User implements CredentialsOwner {

    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleEnum role = RoleEnum.ROLE_ADMIN;
}
