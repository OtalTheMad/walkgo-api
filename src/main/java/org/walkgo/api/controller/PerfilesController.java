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

    @PostMapping
    public Perfiles CreatePerfil(@RequestBody Perfiles perfil) {
        return perfilesRepository.save(perfil);
    }

    @PutMapping("/{id}")
    public Perfiles UpdatePerfil(@PathVariable int id, @RequestBody Perfiles perfilDetails) {
        return perfilesRepository.findById(id).map(perfil -> {
            perfil.setId_usuario(perfilDetails.getId_usuario());
            perfil.setFoto(perfilDetails.getFoto());
            perfil.setPais(perfilDetails.getPais());
            perfil.setBiografia(perfilDetails.getBiografia());
            perfil.setFecha_nac(perfilDetails.getFecha_nac());
            perfil.setEstado(perfilDetails.getEstado());
            return perfilesRepository.save(perfil);
        }).orElseGet(() -> {
            perfilDetails.setId_perfil(id);
            return perfilesRepository.save(perfilDetails);
        });
    }

}
