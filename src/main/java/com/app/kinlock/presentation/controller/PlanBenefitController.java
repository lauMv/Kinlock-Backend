package com.app.kinlock.presentation.controller;

import com.app.kinlock.domain.service.PlanBenefitService;
import com.app.kinlock.presentation.dto.PlanBenefitDto;
import com.app.kinlock.presentation.pojo.PlanBenefitPojo;
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

    @PostMapping("/add")
    public ResponseEntity<PlanBenefitPojo> create(@RequestBody PlanBenefitDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planBenefitService.create(dto));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PlanBenefitPojo> update(@PathVariable Integer id, @RequestBody PlanBenefitDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(planBenefitService.update(id, dto));
    }

    @GetMapping("/list/byPlanId/{id}")
    public ResponseEntity<List<PlanBenefitPojo>> getAllByPlan(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(planBenefitService.getAllByPlan(id));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<PlanBenefitPojo> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(planBenefitService.getPojoById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        planBenefitService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
