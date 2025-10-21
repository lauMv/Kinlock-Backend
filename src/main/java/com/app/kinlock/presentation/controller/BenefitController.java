package com.app.kinlock.presentation.controller;

import com.app.kinlock.common.enums.BenefitsCoverageEnum;
import com.app.kinlock.domain.entity.Benefit;
import com.app.kinlock.domain.service.BenefitService;
import com.app.kinlock.presentation.dto.BenefitDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/benefits")
@AllArgsConstructor
public class BenefitController {

    private final BenefitService benefitService;

    @PostMapping
    public ResponseEntity<Benefit> create(@RequestBody BenefitDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(benefitService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Benefit> update(@PathVariable Integer id, @RequestBody BenefitDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(benefitService.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<Benefit>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(benefitService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Benefit> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(benefitService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        benefitService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/benefitCoverageType")
    public List<String> getBenefitsCoverageTypes() {
        return Arrays.stream(BenefitsCoverageEnum.values())
                .map(BenefitsCoverageEnum::getValue) // only Spanish
                .collect(Collectors.toList());
    }
}
