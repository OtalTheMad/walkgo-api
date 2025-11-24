package org.walkgo.api.repository;

import org.walkgo.api.model.Estadisticas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadisticasRepository extends JpaRepository<Estadisticas, Integer> {

    @Query("SELECT e FROM Estadisticas e WHERE e.id_usuario = :idUsuario")
    Optional<Estadisticas> findByIdUsuario(@Param("idUsuario") Integer idUsuario);
}