package com.app.kinlock.presentation.controller;

import com.app.kinlock.common.enums.VehicleClassificationEnum;
import com.app.kinlock.domain.entity.VehicleCatalog;
import com.app.kinlock.domain.service.VehicleCatalogService;
import com.app.kinlock.presentation.dto.VehicleDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicleCatalog")
@AllArgsConstructor
public class VehicleController {

    VehicleCatalogService vehicleCatalogService;

    @PostMapping
    public ResponseEntity<VehicleCatalog> create(@RequestBody VehicleDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleCatalogService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleCatalog> update(@PathVariable Integer id, @RequestBody VehicleDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleCatalogService.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<VehicleCatalog>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleCatalogService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleCatalog> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleCatalogService.getById(id));
    }

    @GetMapping("/vehicleClassification")
    public List<String> getVehicleClassifications() {
        return Arrays.stream(VehicleClassificationEnum.values())
                .map(VehicleClassificationEnum::getValue) // only Spanish
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        vehicleCatalogService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();    
    }
}
