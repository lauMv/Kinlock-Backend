package com.app.kinlock.presentation.pojo;

import com.app.kinlock.common.enums.RoleEnum;
import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private RoleEnum role;

    public LoginResponse(String token, RoleEnum role) {
        this.token = token;
        this.role = role;
    }
}
