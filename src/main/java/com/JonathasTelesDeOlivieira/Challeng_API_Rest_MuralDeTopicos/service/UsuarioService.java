package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.service;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.domain.model.Usuario;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.application.dto.request.LoginUsuario;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.application.dto.response.DadosTokenJWT;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.infraestruture.security.TokenService;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.domain.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private AuthenticationManager manager;
    private TokenService tokenService;
    private UsuarioRepository repository;


    public UsuarioService(AuthenticationManager manager,
                          TokenService tokenService,
                          UsuarioRepository repository) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.repository = repository;
    }


    public DadosTokenJWT login(LoginUsuario loginUsuario) {
        try {
            var Authenticationtoken =
                    new UsernamePasswordAuthenticationToken(
                            loginUsuario.email(),
                            loginUsuario.senha()
                    );
            var authentication = manager.authenticate(Authenticationtoken);

            var tokenJwt =
                    tokenService.gerarToken((Usuario) authentication.getPrincipal());
            return new DadosTokenJWT(tokenJwt);
        } catch (Exception e) {
            throw new RuntimeException("Erro no método login na service" + e);
        }
    }
}


