package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.in;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTopico(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotBlank String autor,
        @NotBlank String curso
) {}
