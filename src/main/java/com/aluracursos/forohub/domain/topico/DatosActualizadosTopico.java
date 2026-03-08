package com.aluracursos.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizadosTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje) {
}
