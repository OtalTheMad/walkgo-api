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
    public ResponseEntity<List<Perfiles>> GetAllPerfiles() {
        List<Perfiles> list = perfilesService.GetAllPerfiles();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfiles> GetPerfilById(@PathVariable int id) {
        Optional<Perfiles> perfil = perfilesService.GetPerfilById(id);
        return perfil.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
