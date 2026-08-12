package com.app.kinlock.presentation.controller;

import com.app.kinlock.domain.entity.Client;
import com.app.kinlock.domain.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/list")
    public ResponseEntity<List<Client>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Client> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getById(id));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN', 'BROKER')")
    public ResponseEntity<Client> create(@RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(client));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'BROKER')")
    public ResponseEntity<Client> update(@PathVariable Integer id, @RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.update(client, id));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
