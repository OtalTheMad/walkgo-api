package org.walkgo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.walkgo.api.model.RankingEntry;

import java.util.List;
import java.util.Optional;

public interface RankingRepository extends JpaRepository<RankingEntry, Integer> {

    Optional<RankingEntry> findFirstByIdUsuario(Integer idUsuario);

    List<RankingEntry> findAllByOrderByPosicionAsc();

    @Modifying
    @Query(value = """
            INSERT INTO ranking (id_usuario, posicion, estado)
            SELECT u.id, 0, 'activo'
            FROM usuarios u
            LEFT JOIN ranking r ON r.id_usuario = u.id
            WHERE r.id_usuario IS NULL
            """, nativeQuery = true)
    int insertMissingUsers();
}