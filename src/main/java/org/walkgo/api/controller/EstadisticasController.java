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
    public ResponseEntity<List<Estadisticas>> getAllEstadisticas() {
        List<Estadisticas> list = estadisticasService.getAllEstadisticas();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estadisticas> getEstadisticaById(@PathVariable int id) {
        Optional<Estadisticas> estadistica = estadisticasService.getEstadisticaById(id);
        return estadistica.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estadisticas CreateEstadistica(@RequestBody Estadisticas estadistica) {
        return estadisticasRepository.save(estadistica);
    }

    @PutMapping("/{id}")
    public Estadisticas UpdateEstadistica(@PathVariable int id, @RequestBody Estadisticas estadisticaDetails) {
        return estadisticasRepository.findById(id).map(estadistica -> {
            estadistica.setId_usuario(estadisticaDetails.getId_usuario());
            estadistica.setKm_recorrido(estadisticaDetails.getKm_recorrido());
            estadistica.setCalorias_quemadas(estadisticaDetails.getCalorias_quemadas());
            estadistica.setClasificacion(estadisticaDetails.getClasificacion());
            estadistica.setEstado(estadisticaDetails.getEstado());
            return estadisticasRepository.save(estadistica);
        }).orElseGet(() -> {
            estadisticaDetails.setId_estadistica(id);
            return estadisticasRepository.save(estadisticaDetails);
        });
    }

}