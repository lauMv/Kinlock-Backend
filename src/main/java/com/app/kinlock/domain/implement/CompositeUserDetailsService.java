package com.app.kinlock.domain.implement;

import com.app.kinlock.data.AdminRepository;
import com.app.kinlock.data.BrokerRepository;
import com.app.kinlock.domain.service.CredentialsOwner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j   // already present
@Service
@RequiredArgsConstructor
public class CompositeUserDetailsService implements UserDetailsService {

    private final BrokerRepository brokerRepo;
    private final AdminRepository adminRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("🔍 Searching for email: {}", email);

        return brokerRepo.findByEmail(email)
                .map(this::build)
                .orElseGet(() -> adminRepo.findByEmail(email)
                        .map(this::build)
                        .orElseThrow(() -> new UsernameNotFoundException(email)));
    }

    private UserDetails build(CredentialsOwner u) {
        return org.springframework.security.core.userdetails.User
                .withUsername(u.getEmail())
                .password(u.getPassword())
                .authorities(u.getRole().name())
                .build();
    }
}

