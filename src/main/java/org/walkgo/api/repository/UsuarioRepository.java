package org.walkgo.api.repository;

import org.walkgo.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u._usuario = :usuario")
    Optional<Usuario> FindByUsuario(@Param("usuario") String usuario);

    @Query("SELECT u FROM Usuario u WHERE u._id <> :id")
    List<Usuario> FindAllExceptId(@Param("id") Integer id);
}