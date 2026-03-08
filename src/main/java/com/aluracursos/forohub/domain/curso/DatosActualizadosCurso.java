package com.aluracursos.forohub.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DatosActualizadosCurso(
        @NotNull Long id,
        String nombre,
        Categoria categoria) {
}
