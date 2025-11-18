package org.walkgo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.walkgo.api.model.Amigo;

import java.util.List;
import java.util.Optional;

public interface AmigoRepository extends JpaRepository<Amigo, Integer> {

    List<Amigo> findByIdUsuarioOrIdUsuarioAmigo(Integer idUsuario, Integer idUsuarioAmigo);

    @Query("""
           SELECT a
           FROM Amigo a
           WHERE (a.idUsuario = :id1 AND a.idUsuarioAmigo = :id2)
              OR (a.idUsuario = :id2 AND a.idUsuarioAmigo = :id1)
           """)
    Optional<Amigo> findByUsuarios(Integer id1, Integer id2);
}