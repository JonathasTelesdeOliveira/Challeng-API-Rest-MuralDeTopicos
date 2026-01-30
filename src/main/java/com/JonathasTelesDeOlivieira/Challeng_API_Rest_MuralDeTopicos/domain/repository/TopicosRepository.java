package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.domain.repository;

import com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.domain.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicosRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findById(Long id);


    Boolean existsByTituloAndMensagem(String titulo, String mensagem);

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

    void deleteById(Long id);

    Topico getReferenceById(Long id);
}
