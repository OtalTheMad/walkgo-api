package org.walkgo.api.service;

import org.walkgo.api.model.Usuario;
import org.walkgo.api.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImp(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario _usuario = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado."));
        return User.builder()
                .username(_usuario.getUsuario())
                .password("{noop}" + _usuario.getClave())
                .authorities(List.of(() -> "ROLE_USER"))
                .build();
    }
}