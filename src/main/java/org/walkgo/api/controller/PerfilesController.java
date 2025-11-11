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
        List<Perfiles> _list = perfilesService.GetAllPerfiles();
        return ResponseEntity.ok(_list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfiles> GetPerfilById(@PathVariable Integer id) {
        Optional<Perfiles> _perfil = perfilesService.GetPerfilById(id);
        return _perfil.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Perfiles> CreatePerfil(@RequestBody Perfiles _perfil) {
        Perfiles _saved = perfilesService.SavePerfil(_perfil);
        return ResponseEntity.ok(_saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfiles> UpdatePerfil(@PathVariable Integer id, @RequestBody Perfiles _details) {
        Perfiles _updated = perfilesService.UpdatePerfil(id, _details);
        return ResponseEntity.ok(_updated);
    }
}