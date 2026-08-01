package com.app.kinlock.presentation.controller;

import com.app.kinlock.domain.entity.SalesConfig;
import com.app.kinlock.domain.service.SalesConfigService;
import com.app.kinlock.presentation.dto.SalesConfigDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salesConfig")
@AllArgsConstructor
public class SalesConfigController {

    private final SalesConfigService service;

    @GetMapping
    public ResponseEntity<SalesConfig> get() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getConfig());
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SalesConfig> update(@RequestBody SalesConfigDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.setEnabled(dto.getEnabled()));
    }
}
