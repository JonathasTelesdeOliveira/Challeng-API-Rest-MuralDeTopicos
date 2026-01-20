package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.repository;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.entity.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicosRepository extends JpaRepository<Topico, Long> {

    Boolean existsByTituloAndMensagem(String titulo, String mensagem);

}
