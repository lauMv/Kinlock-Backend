package com.app.kinlock.presentation.controller;

import com.app.kinlock.common.enums.RoleEnum;
import com.app.kinlock.common.security.JwtUtil;
import com.app.kinlock.domain.implement.CompositeUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j   // already present
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CompositeUserDetailsService uds;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    record LoginRequest(String email, String password) {
    }

    record LoginResponse(String token, RoleEnum role) {
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest req) {
        log.info("🔑 LOGIN attempt for email: {}", req.email());

        UserDetails ud = uds.loadUserByUsername(req.email());
        log.info("👤 UserDetails loaded: username={}", ud.getUsername());

        boolean ok = encoder.matches(req.password(), ud.getPassword());
        log.info("🔐 Password match result: {}", ok);

        if (!ok) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        String token = jwtUtil.generate(ud.getUsername(),
                ud.getAuthorities().iterator().next().getAuthority());
        return new LoginResponse(token,
                RoleEnum.valueOf(ud.getAuthorities().iterator().next().getAuthority()));
    }
}
