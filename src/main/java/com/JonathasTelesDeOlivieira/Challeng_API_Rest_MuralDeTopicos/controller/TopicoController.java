package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.controller;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.in.DadosBuscarTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.in.DadosCadastroTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.in.TopicoConverter;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.out.DadosDetalhamentoTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.out.DadosListagemTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.entity.Topico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public ResponseEntity<DadosDetalhamentoTopico> cadastrar(@RequestBody @Valid DadosCadastroTopico dto,
                                                             UriComponentsBuilder uriBuilder) {
        Topico topico = topicoService.cadastrarTopico(dto);

        URI uri = uriBuilder
                .path("/topicos{id}")
                .buildAndExpand(topico.getId())
                .toUri();
        return ResponseEntity.created(uri).body(
                converter.paradto(topico));
    }

    @GetMapping("/cursoAno")
    public ResponseEntity<List<DadosDetalhamentoTopico>> buscarTopico(
            @RequestParam String curso,
            @RequestParam int ano,
            Pageable pageable) {
        List<DadosDetalhamentoTopico> lista =
                topicoService.listarCursoPorAno(curso, ano);
        return ResponseEntity.ok(lista);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemTopico>> ListarTopicos(
            @PageableDefault(
                    size = 10,
                    sort = "dataCriacao",
                    direction = Sort.Direction.ASC
            ) Pageable pageable
    ) {
        return ResponseEntity.ok(topicoService.listarTopicos(pageable));
    }
}
