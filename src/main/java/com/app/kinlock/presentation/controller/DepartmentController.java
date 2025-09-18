package com.app.kinlock.presentation.controller;

import com.app.kinlock.domain.entities.Department;
import com.app.kinlock.domain.service.DepartmentService;
import com.app.kinlock.presentation.dto.DepartmentDto;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> create(@RequestBody DepartmentDto dep){
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.create(dep));
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(departmentService.getById(id));
    }
}
