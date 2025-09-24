package com.app.kinlock.presentation.controller;

import com.app.kinlock.common.enums.VehicleClassification;
import com.app.kinlock.domain.entity.Vehicle;
import com.app.kinlock.domain.service.VehicleService;
import com.app.kinlock.presentation.dto.VehicleDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicles")
@AllArgsConstructor
public class VehicleController {

    VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> create(@RequestBody VehicleDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getById(id));
    }

    @GetMapping("/vehicleClassification")
    public List<String> getVehicleClassifications() {
        return Arrays.stream(VehicleClassification.values())
                .map(VehicleClassification::getValue) // only Spanish
                .collect(Collectors.toList());
    }
}
