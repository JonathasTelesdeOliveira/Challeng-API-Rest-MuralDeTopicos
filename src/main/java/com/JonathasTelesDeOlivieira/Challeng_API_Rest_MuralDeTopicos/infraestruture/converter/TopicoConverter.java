package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.infraestruture.converter;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.application.dto.request.DadosCadastroTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.application.dto.response.DadosTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.application.dto.request.DadosUpdateTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.out.*;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.domain.model.Topico;
import org.springframework.stereotype.Component;

@Component
public class TopicoConverter {
    public Topico paraTopicoEntity(DadosCadastroTopico dto) {
        return Topico.builder()
                .titulo(dto.titulo())
                .mensagem(dto.mensagem())
                .autor(dto.autor())
                .curso(dto.curso())
                .build();
    }


    public DadosTopico paraDadoTopico(Topico topico) {
        return new DadosTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getDataCriacao(),
                topico.getStatus()
        );
    }

    public DadosUpdateTopico DadoDTO_builder(Topico topico) {
        return DadosUpdateTopico.builder()
                .id(topico.getId())
                .titulo(topico.getTitulo())
                .mensagem(topico.getMensagem())
                .autor(topico.getAutor())
                .curso(topico.getCurso())
                .dataCriacao(topico.getDataCriacao())
                .status(topico.getStatus())
                .build();
    }

    public Topico paraUpdateTopico(Topico topico, DadosUpdateTopico dadosTopico) {
       return new Topico().builder()
               .id(topico.getId())
               .titulo(dadosTopico.getTitulo() != null ?  dadosTopico.getTitulo() : topico.getTitulo())
               .mensagem(dadosTopico.getMensagem() != null ? dadosTopico.getMensagem() : topico.getMensagem())
               .autor(dadosTopico.getAutor() != null ?  dadosTopico.getAutor() : topico.getAutor())
               .curso(dadosTopico.getCurso() != null ? dadosTopico.getCurso() : topico.getCurso())
               .dataCriacao(topico.getDataCriacao())
               .status(topico.getStatus())
               .build();
    }
}




