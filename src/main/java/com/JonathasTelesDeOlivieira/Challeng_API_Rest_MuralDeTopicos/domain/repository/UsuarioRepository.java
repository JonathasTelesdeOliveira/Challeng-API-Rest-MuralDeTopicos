package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.domain.repository;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);

}

