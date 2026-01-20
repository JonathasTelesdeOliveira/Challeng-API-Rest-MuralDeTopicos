package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.controller;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.in.DadosCadastroTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.in.TopicoConverter;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.out.DadosDetalhamentoTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.entity.Topico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoConverter converter;
    private final TopicoService topicoService;

    public TopicoController(TopicoConverter converter, TopicoService topicoService) {
        this.converter = converter;
        this.topicoService = topicoService;
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoTopico> cadastrar (@RequestBody @Valid DadosCadastroTopico dto,
                                                              UriComponentsBuilder uriBuilder){
        Topico topico = topicoService.cadastrarTopico(dto);

        URI uri = uriBuilder
                .path("/topicos{id}")
                .buildAndExpand(topico.getId())
                .toUri();
        return ResponseEntity.created(uri).body(
                converter.paradto(topico));
    }
}
