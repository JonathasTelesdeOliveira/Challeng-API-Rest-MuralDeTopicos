package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.application.dto.request;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.domain.model.StatusTopico;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DadosUpdateTopico {

    private long id;
    private String titulo;
    private String mensagem;
    private String autor;
    private String curso;
    private LocalDateTime dataCriacao;
    @Enumerated(EnumType.STRING)
    private StatusTopico status;
    private String resposta;

}
