package com.app.kinlock.domain.service;

import com.app.kinlock.presentation.dto.LoginRequest;
import com.app.kinlock.presentation.pojo.LoginResponse;

public interface AuthService {
    LoginResponse authenticate(LoginRequest request);
}