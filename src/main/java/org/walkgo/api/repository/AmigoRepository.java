package org.walkgo.api.repository;

import org.walkgo.api.model.Amigo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AmigoRepository extends JpaRepository<Amigo, Integer> {
    List<Amigo> findByIdUsuario(Integer idUsuario);
}