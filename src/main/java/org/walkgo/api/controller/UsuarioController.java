package org.walkgo.api.controller;

import org.walkgo.api.model.Usuario;
import org.walkgo.api.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository _usuarioRepository;

    public UsuarioController(UsuarioRepository _usuarioRepository) {
        this._usuarioRepository = _usuarioRepository;
    }

    @GetMapping
    public List<Usuario> GetAllUsuarios() {
        return _usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario GetUsuarioById(@PathVariable Integer id) {
        return _usuarioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Usuario CreateUsuario(@RequestBody Usuario usuario) {
        return _usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario UpdateUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioDetails) {
        return _usuarioRepository.findById(id).map(usuario -> {
            usuario.setUsuario(usuarioDetails.getUsuario());
            usuario.setClave(usuarioDetails.getClave());
            usuario.setTotalPasos(usuarioDetails.getTotalPasos());
            usuario.setTotalDistanciaKm(usuarioDetails.getTotalDistanciaKm());
            usuario.setTotalPasosSemanales(usuarioDetails.getTotalPasosSemanales());
            usuario.setRangoSemanal(usuarioDetails.getRangoSemanal());
            usuario.setEstado(usuarioDetails.getEstado());
            usuario.setActualizadoEn(LocalDateTime.now());
            return _usuarioRepository.save(usuario);
        }).orElseGet(() -> {
            usuarioDetails.setId(id);
            return _usuarioRepository.save(usuarioDetails);
        });
    }

    @GetMapping("/disponibles")
    public List<Usuario> GetUsuariosDisponibles(Authentication authentication) {
        String _username = authentication.getName();
        Usuario _actual = _usuarioRepository.FindByUsuario(_username)
                .orElseThrow(() -> new RuntimeException("Usuario autenticado no encontrado."));
        return _usuarioRepository.FindAllExceptId(_actual.getId());
    }
}