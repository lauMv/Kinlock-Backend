package com.app.kinlock.presentation.controller;

import com.app.kinlock.domain.entity.VehicleType;
import com.app.kinlock.domain.service.VehicleTypeService;
import com.app.kinlock.presentation.dto.VehicleTypeDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicleTypes")
@AllArgsConstructor
public class VehicleTypeController {

    private final VehicleTypeService service;

    @GetMapping("/list")
    public ResponseEntity<List<VehicleType>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<VehicleType> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VehicleType> create(@RequestBody VehicleTypeDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VehicleType> update(@PathVariable Integer id, @RequestBody VehicleTypeDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}