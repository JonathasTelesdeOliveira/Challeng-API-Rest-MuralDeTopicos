package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.controller;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.out.DadosCadastroTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.converter.TopicoConverter;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.out.DadosUpdateTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.out.DadosTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.entity.Topico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<DadosTopico> cadastrar(@RequestBody @Valid DadosCadastroTopico dto,
                                                 UriComponentsBuilder uriBuilder) {
        Topico topico = topicoService.cadastrarTopico(dto);

        URI uri = uriBuilder
                .path("/topicos{id}")
                .buildAndExpand(topico.getId())
                .toUri();
        return ResponseEntity.created(uri).body(
                converter.paraDadoTopico(topico));
    }

    @GetMapping("/cursoAno")
    public ResponseEntity<Page<DadosTopico>> buscarTopico(
            @RequestParam String curso,
            @RequestParam int ano,
            Pageable pageable) {
        return ResponseEntity.ok(topicoService.listarCursoPorAno(curso, ano, pageable));
    }

    @GetMapping
    public ResponseEntity<List<DadosTopico>> ListarTopicos(
            @PageableDefault(
                    size = 10,
                    sort = "dataCriacao",
                    direction = Sort.Direction.ASC
            ) Pageable pageable
    ) {
        return ResponseEntity.ok(topicoService.listarTopicos(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosTopico> listarTopicosId(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.ListarTopicoId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<DadosUpdateTopico> updateTopico(@PathVariable Long id,
                                                            @RequestBody @Valid DadosUpdateTopico dto) {
        return ResponseEntity.ok(topicoService.updateTopico(id, dto));
    }
}
