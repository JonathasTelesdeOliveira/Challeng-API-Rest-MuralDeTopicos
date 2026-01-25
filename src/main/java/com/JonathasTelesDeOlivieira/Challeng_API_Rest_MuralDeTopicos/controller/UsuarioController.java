package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.controller;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.out.LoginUsuario;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid  LoginUsuario loginUsuario) {
        return ResponseEntity.ok(usuarioService.login(loginUsuario));
    }
}


