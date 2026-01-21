package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.service;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.in.DadosCadastroTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.in.TopicoConverter;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.out.DadosDetalhamentoTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.out.DadosListagemTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.entity.StatusTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.entity.Topico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.exeptions.TopicoDuplicadoException;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.repository.TopicosRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicoService {
    private final TopicosRepository repository;
    private final TopicoConverter converter;

    public TopicoService(TopicosRepository repository, TopicoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Transactional
    public Topico cadastrarTopico(DadosCadastroTopico dto) {

        if (repository.existsByTituloAndMensagem(dto.titulo(), dto.mensagem())) {
            throw new TopicoDuplicadoException(
                    "Tópico duplicado, já existe mesmo tópico já registrado! "
            );
        }

        Topico topico = converter.paraTopicoEntity(dto);
        topico.setStatus(StatusTopico.Aguardando);
        topico.setDataCriacao(LocalDateTime.now());
        return repository.save(topico);
    }

    public List<DadosListagemTopico> listarTopicos(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(t -> new DadosListagemTopico(
                        t.getId(),
                        t.getTitulo(),
                        t.getMensagem(),
                        t.getAutor(),
                        t.getCurso(),
                        t.getDataCriacao(),
                        t.getStatus()
                )).toList();
    }

    public List<DadosDetalhamentoTopico> listarCursoPorAno(String curso, int ano) {

        LocalDateTime inicio = LocalDateTime.of(ano, 1, 1, 0, 0);
        LocalDateTime fim = LocalDateTime.of(ano, 12, 31, 23, 59, 59);

        List<Topico> topicos =
                repository.findByCursoAndDataCriacaoBetween(curso, inicio, fim);

        return topicos
                .stream()
                .map(converter::paradto)
                .toList();
    }


}




