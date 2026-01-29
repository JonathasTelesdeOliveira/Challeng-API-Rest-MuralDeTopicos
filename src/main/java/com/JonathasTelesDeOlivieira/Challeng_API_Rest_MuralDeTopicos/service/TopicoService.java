package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.service;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.application.dto.request.DadosCadastroTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.infraestruture.converter.TopicoConverter;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.application.dto.response.DadosTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.application.dto.request.DadosUpdateTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.domain.model.StatusTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.domain.model.Topico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.infraestruture.exeption.IdNaoEncontradoException;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.infraestruture.exeption.TopicoDuplicadoException;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.domain.repository.TopicosRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
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

    public List<DadosTopico> listarTopicos(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(t -> new DadosTopico(
                        t.getId(),
                        t.getTitulo(),
                        t.getMensagem(),
                        t.getAutor(),
                        t.getCurso(),
                        t.getDataCriacao(),
                        t.getStatus()
                )).toList();
    }

    public Page<DadosTopico> listarCursoPorAno(String curso, int ano, Pageable pageable) {

        Page<Topico> topicos =
                repository.findByCursoAndAno(curso, ano, pageable);

        return topicos
                .map(converter::paraDadoTopico);
    }

    public DadosTopico ListarTopicoId(Long id) {
        Topico topico = repository.findById(id)
                .orElseThrow(() ->
                        new IdNaoEncontradoException("Erro ao Buscar Id: " + id)
                );
        return converter.paraDadoTopico(topico);
    }

    @Transactional
    public DadosUpdateTopico updateTopico(Long id, DadosUpdateTopico dto) {
        Topico entity = repository.findById(id)
                .orElseThrow(() ->
                        new IdNaoEncontradoException("Erro ao Buscar Id: " + id)
                );

        Topico topicoEntity = converter.paraUpdateTopico(entity, dto);
        return converter.DadoDTO_builder(repository.save(topicoEntity));

    }

    public void excluirTopicoo(Long id) {
        repository.findById(id)
                .orElseThrow(() ->
                        new IdNaoEncontradoException("Erro ao Buscar Id: " + id)
                );
        repository.deleteById(id);
    }
}




