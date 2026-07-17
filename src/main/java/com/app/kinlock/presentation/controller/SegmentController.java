package com.app.kinlock.presentation.controller;

import com.app.kinlock.domain.entity.Segment;
import com.app.kinlock.domain.service.SegmentService;
import com.app.kinlock.presentation.dto.SegmentDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/segments")
@AllArgsConstructor
public class SegmentController {

    private final SegmentService service;

    @GetMapping("/list")
    public ResponseEntity<List<Segment>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Segment> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Segment> create(@RequestBody SegmentDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Segment> update(@PathVariable Integer id, @RequestBody SegmentDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}