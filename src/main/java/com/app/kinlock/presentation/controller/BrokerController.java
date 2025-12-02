package com.app.kinlock.presentation.controller;

import com.app.kinlock.domain.entity.Broker;
import com.app.kinlock.domain.service.BrokerAdminService;
import com.app.kinlock.presentation.dto.BrokerDto;
import com.app.kinlock.presentation.pojo.BrokerPojo;
import com.app.kinlock.presentation.pojo.PlanPojo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/brokers")
@AllArgsConstructor
public class BrokerController {

    @Autowired
    private BrokerAdminService service;

    @PutMapping("/edit")
    @PreAuthorize("hasRole('BROKER')")
    public ResponseEntity<Broker> update(@RequestBody BrokerDto dto) {
        Broker broker = service.update(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<BrokerPojo>> getAllBrokersPojo() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllBrokersPojo());
    }

    @GetMapping("/getBrokerInfo")
    public ResponseEntity<Broker> getBrokerInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getBrokerInfo());
    }

    @GetMapping("/getPlansByBroker")
    public ResponseEntity<List<PlanPojo>> getPlansByBroker() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPlansByBroker());
    }
}
