package com.app.kinlock.presentation.controller;

import com.app.kinlock.domain.service.ClientPlanService;
import com.app.kinlock.presentation.dto.ClientPlanDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientPlans")
@AllArgsConstructor
public class ClientPlanController {

    private final ClientPlanService clientPlanService;

    @PostMapping("/add")
    public ResponseEntity<Void> create(@RequestBody ClientPlanDto dto) {
        clientPlanService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
