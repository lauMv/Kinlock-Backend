package com.app.kinlock.presentation.controller;

import com.app.kinlock.common.enums.EngineTypeEnum;
import com.app.kinlock.common.enums.VehicleClassificationEnum;
import com.app.kinlock.domain.entity.VehicleCatalog;
import com.app.kinlock.domain.service.VehicleCatalogService;
import com.app.kinlock.presentation.dto.VehicleDto;
import com.app.kinlock.presentation.pojo.VehiclePojo;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicleCatalog")
@AllArgsConstructor
public class VehicleCatalogController {

    VehicleCatalogService vehicleCatalogService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('BROKER','ADMIN')")
    public ResponseEntity<VehicleCatalog> create(@RequestBody VehicleDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleCatalogService.create(dto));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('BROKER','ADMIN')")
    public ResponseEntity<VehicleCatalog> update(@PathVariable Integer id, @RequestBody VehicleDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleCatalogService.update(id, dto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<VehiclePojo>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleCatalogService.getAllPojo());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<VehiclePojo> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleCatalogService.getPojoById(id));
    }

    @GetMapping("/vehicleClassification")
    public List<String> getVehicleClassifications() {
        return Arrays.stream(VehicleClassificationEnum.values())
                .map(VehicleClassificationEnum::getValue) // only Spanish
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        vehicleCatalogService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/allBrands")
    public ResponseEntity<List<String>> getAllBrands() {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleCatalogService.getAllBrands());
    }

    @GetMapping("/allModelsByBrand")
    public ResponseEntity<List<String>> getAllModelsByBrand(@PathParam("brand") String brand) {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleCatalogService.getAllModelsByBrand(brand));
    }

    @GetMapping("/allEngineTypes")
    public List<String> getAllEngineTypes() {
        return Arrays.stream(EngineTypeEnum.values())
                .map(EngineTypeEnum::getValue)
                .collect(Collectors.toList());
    }
}
