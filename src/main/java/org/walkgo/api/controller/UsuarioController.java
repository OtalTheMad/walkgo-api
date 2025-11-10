package org.walkgo.api.controller;

import org.walkgo.api.model.Usuario;
import org.walkgo.api.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

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
    public Usuario GetUsuarioById(@PathVariable Long id) {
        return _usuarioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Usuario CreateUsuario(@RequestBody Usuario usuario) {
        return _usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario UpdateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        return _usuarioRepository.findById(id).map(usuario -> {
            usuario.SetUsuario(usuarioDetails.GetUsuario());
            usuario.SetClave(usuarioDetails.GetClave());
            usuario.SetTotal_Pasos(usuarioDetails.GetTotal_Pasos());
            usuario.SetTotal_Distancia_Km(usuarioDetails.GetTotal_Distancia_Km());
            usuario.SetTotal_Pasos_Semanales(usuarioDetails.GetTotal_Pasos_Semanales());
            usuario.SetRango_Semanal(usuarioDetails.GetRango_Semanal());
            usuario.SetEstado(usuarioDetails.GetEstado());
            usuario.SetActualizado_En(LocalDateTime.now());
            return _usuarioRepository.save(usuario);
        }).orElseGet(() -> {
            usuarioDetails.SetId(id);
            return _usuarioRepository.save(usuarioDetails);
        });
    }

}