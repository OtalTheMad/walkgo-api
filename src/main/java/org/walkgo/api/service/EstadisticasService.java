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

    public List<Estadisticas> GetAllEstadisticas() {
        List<Estadisticas> _list = estadisticasRepository.findAll();
        return _list;
    }

    public Optional<Estadisticas> GetEstadisticaById(Integer _id) {
        Optional<Estadisticas> _estadistica = estadisticasRepository.findByIdUsuario(_id);
        return _estadistica;
    }

    public Estadisticas SaveEstadistica(Estadisticas _estadistica) {
        return estadisticasRepository.save(_estadistica);
    }

    public Estadisticas UpdateEstadistica(Integer _id, Estadisticas _details) {
        return estadisticasRepository.findById(_id).map(_estadistica -> {
            _estadistica.setId_usuario(_details.getId_usuario());
            _estadistica.setKm_recorrido(_details.getKm_recorrido());
            _estadistica.setCalorias_quemadas(_details.getCalorias_quemadas());
            _estadistica.setClasificacion(_details.getClasificacion());
            _estadistica.setEstado(_details.getEstado());
            return estadisticasRepository.save(_estadistica);
        }).orElseGet(() -> {
            _details.setId_estadistica(_id);
            return estadisticasRepository.save(_details);
        });
    }
}