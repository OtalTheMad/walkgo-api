package org.walkgo.api.controller;

import org.walkgo.api.model.LoginRequest;
import org.walkgo.api.model.LoginResponse;
import org.walkgo.api.model.Usuario;
import org.walkgo.api.repository.UsuarioRepository;
import org.walkgo.api.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public AuthController(UsuarioRepository usuarioRepository, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> Login(@RequestBody LoginRequest loginRequest) {
        Usuario _usuario = usuarioRepository.findByUsuario(loginRequest.getUsername())
                .orElse(null);
        if (_usuario == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!_usuario.getClave().equals(loginRequest.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserDetails _userDetails = User.builder()
                .username(_usuario.getUsuario())
                .password("{noop}" + _usuario.getClave())
                .authorities(List.of(() -> "ROLE_USER"))
                .build();
        String _token = jwtService.GenerateToken(_userDetails, _usuario.getId());
        LoginResponse _response = new LoginResponse(_token);
        return new ResponseEntity<>(_response, HttpStatus.OK);
    }
}