package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.infra.security;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final UsuarioRepository repository;
    private TokenService tokenService;


    public SecurityFilter(TokenService tokenService,  UsuarioRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {
        System.out.println("Iniciando Filter...............");
        if (request.getRequestURI().equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        var tokenJWT = recuperarToken(request);
        if (tokenJWT != null) {
            try {
                var subject = tokenService.getSubject(tokenJWT);
                var usuario = repository.findByEmail(subject);
                if (usuario != null) {

                    var authentication = new UsernamePasswordAuthenticationToken(
                            usuario,
                            null,
                            usuario.getAuthorities()
                    );
                    System.out.println("Passou na autentication var authentication = new UsernamePasswordAuthenticationToken(\n" +
                            "                    usuario,\n" +
                            "                    null,\n" +
                            "                    usuario.getAuthorities()................");
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authentication);
                    System.out.println("Passou na autentication var authentication = new UsernamePasswordAuthenticationToken(\n" +
                            "                     SecurityContextHolder\n" +
                            "                    .getContext()\n" +
                            "                    .setAuthentication(authentication);");
                }
            } catch (Exception e) {
                // Token inválido, expirado ou adulterado
                SecurityContextHolder.clearContext();
                // NÃO lança exceção
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }
        return null;
    }
}
