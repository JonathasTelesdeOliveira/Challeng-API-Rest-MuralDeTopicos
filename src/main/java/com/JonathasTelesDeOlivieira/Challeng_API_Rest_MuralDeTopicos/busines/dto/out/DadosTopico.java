package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.out;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.entity.StatusTopico;
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
