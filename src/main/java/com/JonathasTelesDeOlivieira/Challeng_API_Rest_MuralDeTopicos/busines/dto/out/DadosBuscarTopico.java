package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.out;

import java.time.LocalDateTime;

public record DadosBuscarTopico(
        String curso,
        LocalDateTime dataCriacao
) {
}



