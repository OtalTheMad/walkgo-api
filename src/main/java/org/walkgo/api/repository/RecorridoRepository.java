package org.walkgo.api.repository;

import org.walkgo.api.model.Recorrido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RecorridoRepository extends JpaRepository<Recorrido, Integer> {
    List<Recorrido> findByIdUsuarioAndFechaBetween(Integer idUsuario, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}