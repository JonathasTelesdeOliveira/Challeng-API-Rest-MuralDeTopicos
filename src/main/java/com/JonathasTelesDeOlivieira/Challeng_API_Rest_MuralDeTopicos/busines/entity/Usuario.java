package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Table(name="usuario")
@Entity(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String mensagem;
    private String topico;
    private LocalDateTime dataCriacao;
    private String autor;
    private String solucao;
}
