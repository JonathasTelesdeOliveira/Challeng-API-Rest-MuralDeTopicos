package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.repository;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.busines.entity.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TopicosRepository extends JpaRepository<Topico, Long> {

    Boolean existsByTituloAndMensagem(String titulo, String mensagem);

    List<Topico> findByCursoAndDataCriacaoBetween(String curso,
                                                  LocalDateTime inicio,
                                                  LocalDateTime fim);

    @Query("""
                select t from Topico t
                where t.curso = :curso
                and year(t.dataCriacao) = :ano
            """)
    Page<Topico> findByCursoAndAno(
            @Param("curso") String curso,
            @Param("ano") int ano,
            Pageable pageable
    );
}
