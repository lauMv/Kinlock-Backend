package com.app.kinlock.presentation.controller;

import com.app.kinlock.domain.entity.Broker;
import com.app.kinlock.domain.service.BrokerAdminService;
import com.app.kinlock.presentation.dto.BrokerDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminBrokerController {

    private final BrokerAdminService service;

    @PostMapping("/add/brokers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Broker> createBroker(@Valid @RequestBody BrokerDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.createBroker(dto));
    }


}
