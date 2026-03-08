package com.aluracursos.forohub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    @Query("""
            SELECT t FROM Topico t WHERE t.curso.nombre = :nombreCurso 
            AND YEAR(t.fechaCreacion) = :año 
            AND t.activo = true
            """)
    Page<Topico> findByCursoyAño(String nombreCurso, int año, Pageable paginacion);

    Page<Topico> findAllByActivoTrue(Pageable paginacion);
}
