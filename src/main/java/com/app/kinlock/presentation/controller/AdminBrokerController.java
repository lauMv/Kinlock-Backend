package com.app.kinlock.presentation.controller;

import com.app.kinlock.data.BrokerRepository;
import com.app.kinlock.domain.entity.Broker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminBrokerController {

    private final BrokerRepository brokerRepo;
    private final PasswordEncoder encoder;

    record CreateBrokerRequest(String name, Long ci, String email,
                               String rawPassword, String logo) {}

    @PostMapping("/brokers")
    @PreAuthorize("hasRole('ADMIN')")
    public Broker createBroker(@Valid @RequestBody CreateBrokerRequest dto) {
        if (brokerRepo.existsByEmail(dto.email()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used");

        Broker b = new Broker();
        b.setName(dto.name());
        b.setCi(dto.ci());
        b.setEmail(dto.email());
        b.setPassword(encoder.encode(dto.rawPassword()));
        b.setLogo(dto.logo());
        return brokerRepo.save(b);
    }
}
