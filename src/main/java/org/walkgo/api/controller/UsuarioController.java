package org.walkgo.api.controller;

import org.walkgo.api.model.Usuario;
import org.walkgo.api.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    private final UsuarioRepository _usuarioRepository;

    public UserController(UsuarioRepository _usuarioRepository) {
        this._usuarioRepository = _usuarioRepository;
    }

    @GetMapping
    public List<Usuario> GetAllUsuarios() {
        return _usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario GetUsuarioById(@PathVariable Long id) {
        return _usuarioRepository.findById(id).orElse(null);
    }
}
