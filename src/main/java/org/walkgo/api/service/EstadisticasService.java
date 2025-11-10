package org.walkgo.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.walkgo.api.model.Estadisticas;
import org.walkgo.api.repository.EstadisticasRepository;

@Service
public class EstadisticasService {

    private final EstadisticasRepository estadisticasRepository;

    public EstadisticasService(EstadisticasRepository estadisticasRepository) {
        this.estadisticasRepository = estadisticasRepository;
    }

    public List<Estadisticas> getAllEstadisticas() {
        return estadisticasRepository.findAll();
    }

    public Optional<Estadisticas> getEstadisticaById(int id) {
        return estadisticasRepository.findById(id);
    }
}