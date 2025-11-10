package org.walkgo.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.walkgo.api.model.Perfiles;
import org.walkgo.api.service.PerfilesService;

@RestController
@RequestMapping("/api/perfiles")
public class PerfilesController {

    private final PerfilesService perfilesService;

    public PerfilesController(PerfilesService perfilesService) {
        this.perfilesService = perfilesService;
    }

    @GetMapping
    public ResponseEntity<List<Perfiles>> getAllPerfiles() {
        List<Perfiles> list = perfilesService.getAllPerfiles();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfiles> getPerfilById(@PathVariable int id) {
        Optional<Perfiles> perfil = perfilesService.getPerfilById(id);
        return perfil.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}