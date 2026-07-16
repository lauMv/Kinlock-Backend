package com.app.kinlock.presentation.controller;

import com.app.kinlock.domain.entity.PlanType;
import com.app.kinlock.domain.service.PlanTypeService;
import com.app.kinlock.presentation.dto.PlanTypeDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planTypes")
@AllArgsConstructor
public class PlanTypeController {

    private final PlanTypeService service;

    @GetMapping("/list")
    public ResponseEntity<List<PlanType>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<PlanType> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PlanType> create(@RequestBody PlanTypeDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PlanType> update(@PathVariable Integer id, @RequestBody PlanTypeDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}