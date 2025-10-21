package com.app.kinlock.presentation.controller;

import com.app.kinlock.domain.entity.Regional;
import com.app.kinlock.domain.service.RegionalService;
import com.app.kinlock.presentation.dto.RegionalDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regionals")
@AllArgsConstructor
public class RegionalController {

    private RegionalService regionalService;

    @PostMapping
    public ResponseEntity<Regional> create(@RequestBody RegionalDto dep){
        return ResponseEntity.status(HttpStatus.CREATED).body(regionalService.create(dep));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Regional> update(@PathVariable Integer id, @RequestBody RegionalDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(regionalService.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<Regional>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(regionalService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Regional> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(regionalService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        regionalService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
