package com.app.kinlock.presentation.controller;

import com.app.kinlock.domain.entity.OfferColumnConfig;
import com.app.kinlock.domain.service.OfferColumnConfigService;
import com.app.kinlock.presentation.dto.OfferColumnConfigDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offerColumnConfig")
@AllArgsConstructor
public class OfferColumnConfigController {

    private final OfferColumnConfigService service;

    @GetMapping("/list")
    public ResponseEntity<List<OfferColumnConfig>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OfferColumnConfig> update(@PathVariable Integer id, @RequestBody OfferColumnConfigDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateEnabled(id, dto.getEnabled()));
    }
}