package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.in;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DadosBuscarTopico(
        String curso,
        LocalDateTime dataCriacao
) {
}



