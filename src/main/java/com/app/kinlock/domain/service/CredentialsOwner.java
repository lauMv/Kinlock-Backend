package com.app.kinlock.domain.service;

import com.app.kinlock.common.enums.RoleEnum;

public interface CredentialsOwner {

    Integer getId();
    String getEmail();
    String getPassword();
    RoleEnum getRole();
}