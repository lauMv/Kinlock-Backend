package com.app.kinlock.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFacade {

    public String getEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public boolean isBroker() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .anyMatch(g -> g.getAuthority().equals("ROLE_BROKER"));
    }
}