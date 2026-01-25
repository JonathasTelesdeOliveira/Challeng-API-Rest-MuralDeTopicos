package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.service;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.domain.Usuario;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.out.LoginUsuario;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private AuthenticationManager manager;

    public UsuarioService(AuthenticationManager manager) {
        this.manager = manager;
    }


    public Usuario login(LoginUsuario loginUsuario) {
        var token = new UsernamePasswordAuthenticationToken(
                loginUsuario.email(),
                loginUsuario.senha()
        );
        var authentication = manager.authenticate(token);
        return (Usuario) authentication.getPrincipal();
    }
}
