package com.app.kinlock.presentation.controller;

import com.app.kinlock.domain.entity.Insurance;
import com.app.kinlock.domain.service.InsuranceService;
import com.app.kinlock.presentation.dto.InsuranceDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurances")
@AllArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;

    @PostMapping
    public ResponseEntity<Insurance> create(@RequestBody InsuranceDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(insuranceService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Insurance> update(@PathVariable Integer id, @RequestBody InsuranceDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(insuranceService.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<Insurance>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(insuranceService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insurance> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(insuranceService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        insuranceService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
