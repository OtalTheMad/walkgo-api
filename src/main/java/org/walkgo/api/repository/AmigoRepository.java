package org.walkgo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.walkgo.api.model.Amigo;

import java.util.List;
import java.util.Optional;

public interface AmigoRepository extends JpaRepository<Amigo, Integer> {

    List<Amigo> findByIdUsuario(Integer idUsuario);

    Optional<Amigo> findByIdUsuarioAndIdUsuarioAmigo(Integer idUsuario, Integer idUsuarioAmigo);
}