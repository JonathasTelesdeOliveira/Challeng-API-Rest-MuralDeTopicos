package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.in;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.out.DadosDetalhamentoTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.out.DadosListagemTopico;
import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.entity.Topico;
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

    public Topico paraBuscaTopicoEntity(DadosBuscarTopico dto) {
        return Topico.builder()
                .curso(dto.curso())
                .dataCriacao(dto.dataCriacao())
                .build();
    }

    public DadosListagemTopico paradtoListagem(Topico topico) {
        return new DadosListagemTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getDataCriacao(),
                topico.getStatus()
        );
    }




}





//    // Envia um DTO e retorna uma entity
//    public TopicoDTO paraTopicoDTO(Topico entity){
//         return TopicoDTO.builder()
//               .titulo(entity.getTitulo())
//                .mensagem(entity.getMensagem())
//                .autor(entity.getAutor())
//                .curso(entity.getCurso())
//                .dataCriacao(entity.getDataCriacao())
//                .status(StatusTopico.Aguardando.name())
//                .resposta(entity.getResposta())
//                .build();
//    }



