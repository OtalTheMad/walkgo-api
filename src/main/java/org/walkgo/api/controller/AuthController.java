package org.walkgo.api.controller;

import org.walkgo.api.model.LoginRequest;
import org.walkgo.api.model.LoginResponse;
import org.walkgo.api.model.RegisterRequest;
import org.walkgo.api.model.Usuario;
import org.walkgo.api.model.Perfiles;
import org.walkgo.api.model.Estadisticas;
import org.walkgo.api.repository.UsuarioRepository;
import org.walkgo.api.repository.PerfilesRepository;
import org.walkgo.api.repository.EstadisticasRepository;
import org.walkgo.api.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PerfilesRepository perfilesRepository;
    private final EstadisticasRepository estadisticasRepository;

    public AuthController(UsuarioRepository usuarioRepository,
                          JwtService jwtService,
                          PerfilesRepository perfilesRepository,
                          EstadisticasRepository estadisticasRepository) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.perfilesRepository = perfilesRepository;
        this.estadisticasRepository = estadisticasRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> Login(@RequestBody LoginRequest loginRequest) {
        Usuario _usuario = usuarioRepository.FindByUsuario(loginRequest.getUsername())
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

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest registerRequest) {
        String _usuarioNuevo = registerRequest.getUsuario();
        String _claveNueva = registerRequest.getClave();

        if (_usuarioNuevo == null || _usuarioNuevo.trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (_claveNueva == null || _claveNueva.trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (_usuarioNuevo.length() > 30 || _claveNueva.length() > 30) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean _existe = usuarioRepository.FindByUsuario(_usuarioNuevo).isPresent();
        if (_existe) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Usuario _usuario = new Usuario();
        _usuario.setUsuario(_usuarioNuevo);
        _usuario.setClave(_claveNueva);
        _usuario.setTotalPasos(0);
        _usuario.setTotalDistanciaKm(0.0);
        _usuario.setTotalPasosSemanales(0);
        _usuario.setRangoSemanal(0);
        _usuario.setEstado(true);

        Usuario _guardado = usuarioRepository.save(_usuario);

        Perfiles _perfil = new Perfiles();
        _perfil.setId_usuario(_guardado.getId());
        _perfil.setFoto(null);
        _perfil.setPais(null);
        _perfil.setBiografia(null);
        _perfil.setFecha_nac(null);
        _perfil.setEstado("activo");
        perfilesRepository.save(_perfil);

        Estadisticas _estadisticas = new Estadisticas();
        _estadisticas.setId_usuario(_guardado.getId());
        _estadisticas.setKm_recorrido(0);
        _estadisticas.setCalorias_quemadas("0");
        _estadisticas.setClasificacion("principiante");
        _estadisticas.setEstado("activo");
        estadisticasRepository.save(_estadisticas);

        UserDetails _userDetails = User.builder()
                .username(_guardado.getUsuario())
                .password("{noop}" + _guardado.getClave())
                .authorities(List.of(() -> "ROLE_USER"))
                .build();
        String _token = jwtService.GenerateToken(_userDetails, _guardado.getId());
        LoginResponse _response = new LoginResponse(_token);

        return new ResponseEntity<>(_response, HttpStatus.CREATED);
    }
}
