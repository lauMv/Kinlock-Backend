package com.app.kinlock.presentation.controller;

import com.app.kinlock.domain.service.PlanService;
import com.app.kinlock.presentation.dto.FilterPlanDto;
import com.app.kinlock.presentation.dto.PlanDto;
import com.app.kinlock.presentation.dto.SendEmailDto;
import com.app.kinlock.presentation.pojo.PlanPojo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/plans")
@AllArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'BROKER')")
    public ResponseEntity<PlanPojo> create(@RequestBody PlanDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planService.create(dto));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'BROKER')")
    public ResponseEntity<PlanPojo> update(@PathVariable Integer id, @RequestBody PlanDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(planService.update(id, dto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<PlanPojo>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(planService.getAllPojo());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<PlanPojo> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(planService.getPojoById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<List<PlanPojo>> search(@RequestBody FilterPlanDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(planService.search(dto));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        planService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/sendEmail/{id}")
    public ResponseEntity<Void> sendEmail(@RequestBody SendEmailDto dto) {
        planService.sendPlanToEmail(dto);
        return ResponseEntity.ok().build();
    }

}
