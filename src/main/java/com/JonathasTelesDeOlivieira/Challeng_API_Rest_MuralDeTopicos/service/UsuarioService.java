package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.service;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.domain.Usuario;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.infra.security.DadosTokenJWT;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.out.LoginUsuario;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.infra.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private AuthenticationManager manager;
    private TokenService tokenService;


    public UsuarioService(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }


    public DadosTokenJWT login(LoginUsuario loginUsuario) {
        var Authenticationtoken =
                new UsernamePasswordAuthenticationToken(
                        loginUsuario.email(),
                        loginUsuario.senha()
                );
        var authentication = manager.authenticate(Authenticationtoken);

        var tokenJwt =
                tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return new DadosTokenJWT(tokenJwt);
    }
}



