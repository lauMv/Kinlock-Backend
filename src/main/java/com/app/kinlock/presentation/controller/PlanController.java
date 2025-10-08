package com.app.kinlock.presentation.controller;

import com.app.kinlock.domain.service.PlanService;
import com.app.kinlock.presentation.dto.PlanDto;
import com.app.kinlock.presentation.pojo.PlanPojo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
@AllArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping
    public ResponseEntity<PlanPojo> create(@RequestBody PlanDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanPojo> update(@PathVariable Integer id, @RequestBody PlanDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(planService.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<PlanPojo>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(planService.getAllPojo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanPojo> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(planService.getPojoById(id));
    }

}
