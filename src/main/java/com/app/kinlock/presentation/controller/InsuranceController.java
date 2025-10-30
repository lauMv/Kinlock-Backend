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

    @PostMapping("/add")
    public ResponseEntity<Insurance> create(@RequestBody InsuranceDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(insuranceService.create(dto));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Insurance> update(@PathVariable Integer id, @RequestBody InsuranceDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(insuranceService.update(id, dto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Insurance>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(insuranceService.getAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Insurance> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(insuranceService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        insuranceService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
