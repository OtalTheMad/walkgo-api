package org.walkgo.api.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.walkgo.api.model.Estadisticas;
import org.walkgo.api.service.EstadisticasService;

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticasController {

    private final EstadisticasService estadisticasService;

    public EstadisticasController(EstadisticasService estadisticasService) {
        this.estadisticasService = estadisticasService;
    }

    @GetMapping
    public ResponseEntity<List<Estadisticas>> GetAllEstadisticas() {
        List<Estadisticas> _list = estadisticasService.GetAllEstadisticas();
        return ResponseEntity.ok(_list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estadisticas> GetEstadisticaById(@PathVariable Integer id) {
        Optional<Estadisticas> _estadistica = estadisticasService.GetEstadisticaById(id);
        return _estadistica.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Estadisticas> CreateEstadistica(@RequestBody Estadisticas _estadistica) {
        Estadisticas _saved = estadisticasService.SaveEstadistica(_estadistica);
        return ResponseEntity.ok(_saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estadisticas> UpdateEstadistica(@PathVariable Integer id, @RequestBody Estadisticas _details) {
        Estadisticas _updated = estadisticasService.UpdateEstadistica(id, _details);
        return ResponseEntity.ok(_updated);
    }
}