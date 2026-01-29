package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.application.controller;


import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.application.dto.request.LoginUsuario;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.application.dto.response.DadosTokenJWT;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private UsuarioService usuarioService;

    public AutenticacaoController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<DadosTokenJWT> login(@RequestBody @Valid LoginUsuario loginUsuario) {
        return ResponseEntity.ok(usuarioService.login(loginUsuario));
    }
}


