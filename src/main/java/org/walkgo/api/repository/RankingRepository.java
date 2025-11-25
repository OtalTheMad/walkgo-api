package org.walkgo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.walkgo.api.model.RankingEntry;
import org.walkgo.api.model.RankingRow;

import java.util.List;

public interface RankingRepository extends JpaRepository<RankingEntry, Integer> {

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = """
        INSERT INTO ranking (id_usuario, posicion, estado)
        SELECT u.id, 0, 'activo'
        FROM usuarios u
        LEFT JOIN ranking r ON r.id_usuario = u.id
        WHERE r.id_usuario IS NULL
        """, nativeQuery = true)
    int insertMissingUsers();

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = """
        UPDATE ranking r
        JOIN (
            SELECT ra.id_usuario AS id_usuario,
                   ROW_NUMBER() OVER (ORDER BY u.total_distancia_km DESC) AS pos
            FROM ranking ra
            JOIN usuarios u ON u.id = ra.id_usuario
            WHERE ra.estado = 'activo'
        ) t ON t.id_usuario = r.id_usuario
        SET r.posicion = t.pos
        WHERE r.estado = 'activo'
        """, nativeQuery = true)
    int recomputePositions();

    @Query(value = """
        SELECT 
            r.posicion AS posicion,
            r.id_usuario AS idUsuario,
            u.usuario AS usuario,
            u.total_distancia_km AS totalDistanciaKm
        FROM ranking r
        JOIN usuarios u ON u.id = r.id_usuario
        WHERE r.estado = 'activo'
        ORDER BY r.posicion ASC
        """, nativeQuery = true)
    List<RankingRow> findRanking();
}