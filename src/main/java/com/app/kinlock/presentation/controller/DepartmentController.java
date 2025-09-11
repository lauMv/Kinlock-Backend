package com.app.kinlock.presentation.controller;

import com.app.kinlock.domain.entities.Department;
import com.app.kinlock.domain.service.DepartmentService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> create(Department dep){
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.create(dep));
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.getAll());
    }

    @GetMapping("/id")
    public ResponseEntity<Department> getAll(@PathParam("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.getById(id));
    }
}
