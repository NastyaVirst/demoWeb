package com.example.demoWeb.controllers;

import com.example.demoWeb.entities.EntityWagon;
import com.example.demoWeb.services.ServicesWagon;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Tag(name = "Вагоны", description = "Информация по вагонам")

public class ControllerWagon {
    ServicesWagon servicesWagon;

    public ControllerWagon(ServicesWagon servicesWagon) {
        this.servicesWagon = servicesWagon;
    }

    @GetMapping(value = "/wagons", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получить информацию по всем вагонам")
    public ResponseEntity<List<EntityWagon>> getAllWagon() {
        List<EntityWagon> listWagons = servicesWagon.readWagons();
        if (listWagons.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "wagons not found"
            );
        }
        return new ResponseEntity<>(listWagons, HttpStatus.OK);
    }

    @PostMapping(value = "/wagons/new", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Добавить вагон")
    public ResponseEntity<EntityWagon> createWagon(@RequestBody EntityWagon entityWagon) {
        return new ResponseEntity<>(servicesWagon.createWagon(entityWagon), HttpStatus.CREATED);

    }

    @PutMapping(value = "/wagons/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Обновить информацию по вагону")
    public ResponseEntity<EntityWagon> updateWagon(@PathVariable Long id, @RequestBody EntityWagon entityWagon) {
        return new ResponseEntity<>(servicesWagon.updateWagon(id, entityWagon), HttpStatus.OK);
    }

    @DeleteMapping("/wagons/del/{id}")
    @Operation(summary = "Удалить вагон")
    public ResponseEntity<Long> deleteWagon(@PathVariable Long id) {
        return new ResponseEntity<>(servicesWagon.deleteWagon(id),HttpStatus.OK);
    }

}
