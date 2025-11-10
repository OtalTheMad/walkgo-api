package org.walkgo.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String _authHeader = request.getHeader("Authorization");
        if (_authHeader == null || !_authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String _token = _authHeader.substring(7);
        String _username = jwtService.ExtractUsername(_token);
        if (_username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var _userDetails = userDetailsService.loadUserByUsername(_username);
            if (jwtService.IsTokenValid(_token, _userDetails)) {
                var _authToken = new UsernamePasswordAuthenticationToken(
                        _userDetails, null, _userDetails.getAuthorities());
                _authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(_authToken);
            }
        }

        chain.doFilter(request, response);
    }
}
