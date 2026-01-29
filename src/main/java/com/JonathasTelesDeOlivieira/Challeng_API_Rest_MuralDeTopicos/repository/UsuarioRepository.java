package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.repository;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);

}

