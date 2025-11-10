package org.walkgo.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.walkgo.api.security.JwtService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authManager, UserDetailsService userDetailsService, JwtService jwtService) {
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> Login(@RequestBody Map<String, String> body) {
        String _username = body.get("username");
        String _password = body.get("password");
        authManager.authenticate(new UsernamePasswordAuthenticationToken(_username, _password));
        var _user = userDetailsService.loadUserByUsername(_username);
        String _token = jwtService.GenerateToken(_user);
        return ResponseEntity.ok(Map.of("token", _token));
    }
}