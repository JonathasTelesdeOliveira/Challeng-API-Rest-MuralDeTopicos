package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.application.dto.response;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.domain.model.StatusTopico;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DadosTopico(
        Long id,
        String titulo,
        String mensagem,
        String autor,
        String curso,
        LocalDateTime dataCriacao,
        StatusTopico status
) {
}
