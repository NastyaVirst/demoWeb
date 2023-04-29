package com.example.demoWeb.controllers;

import ch.qos.logback.classic.Logger;
import com.example.demoWeb.entities.EntityNomenclature;
import com.example.demoWeb.services.ServicesNomenclature;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Tag(name = "Номенклатура", description = "Информация по номенклатуре")
/*@SecurityRequirement(name = "user")*/
public class ControllerNomenclature {
    ServicesNomenclature servicesNomenclature;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ControllerNomenclature.class);

    public ControllerNomenclature(ServicesNomenclature servicesNomenclature) {
        this.servicesNomenclature = servicesNomenclature;
    }
    @GetMapping(value = "/nomenclature", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получить информацию по всей номенклатуре")
    public ResponseEntity<List<EntityNomenclature>> getAllNomenclature() {
        logger.info("get info about nomenclature");
        List<EntityNomenclature> listNomenclature = servicesNomenclature.readNomenclature();
        if (listNomenclature.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "nomenclature not found"
            );
        }
        return new ResponseEntity<>(listNomenclature, HttpStatus.OK);
    }

    @PostMapping(value = "/nomenclature/new", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Добавить номенклатуру")
    public ResponseEntity<EntityNomenclature> createNomenclature(@RequestBody EntityNomenclature entityNomenclature) {
        return new ResponseEntity<>(servicesNomenclature.createNomenclature(entityNomenclature), HttpStatus.CREATED);
    }

    @PutMapping(value = "/nomenclature/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Обновить информацию по номенклатуре")
    public ResponseEntity<EntityNomenclature> updateWagon(@PathVariable Long id, @RequestBody EntityNomenclature entityNomenclature) {
        return new ResponseEntity<>(servicesNomenclature.updateNomenclature(id, entityNomenclature), HttpStatus.OK);
    }

    @DeleteMapping("/nomenclature/{id}")
    @Operation(summary = "Удалить номенклатуру")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public Long deleteNomenclature(@PathVariable Long id) {
        return servicesNomenclature.deleteNomenclature(id);
    }

}
