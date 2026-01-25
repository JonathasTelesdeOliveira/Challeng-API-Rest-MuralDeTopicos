package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.dto.out;

import jakarta.validation.constraints.NotBlank;

public record LoginUsuario(
        @NotBlank String email,
        @NotBlank String senha
) {
}
