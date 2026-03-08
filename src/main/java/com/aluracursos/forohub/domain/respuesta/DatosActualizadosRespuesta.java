package com.aluracursos.forohub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizadosRespuesta(
        @NotBlank String mensaje) {
}
