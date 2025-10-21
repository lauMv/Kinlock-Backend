package com.app.kinlock.presentation.controller;

import com.app.kinlock.domain.entity.PlanBenefit;
import com.app.kinlock.domain.service.PlanBenefitService;
import com.app.kinlock.presentation.dto.PlanBenefitDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planBenefits")
@AllArgsConstructor
public class PlanBenefitController {

    private final PlanBenefitService planBenefitService;

    @PostMapping
    public ResponseEntity<PlanBenefit> create(@RequestBody PlanBenefitDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planBenefitService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanBenefit> update(@PathVariable Integer id, @RequestBody PlanBenefitDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(planBenefitService.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<PlanBenefit>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(planBenefitService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanBenefit> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(planBenefitService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        planBenefitService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
