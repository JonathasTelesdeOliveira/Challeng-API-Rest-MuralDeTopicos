package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.application.dto.response;

import java.time.LocalDateTime;

public record DadosBuscarTopico(
        String curso,
        LocalDateTime dataCriacao
) {
}



