package org.walkgo.api.repository;

import org.walkgo.api.model.Perfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilesRepository extends JpaRepository<Perfiles, Integer> {

    @Query("SELECT p FROM Perfiles p WHERE p.id_usuario = :idUsuario")
    Optional<Perfiles> findByIdUsuario(@Param("idUsuario") Integer idUsuario);
}