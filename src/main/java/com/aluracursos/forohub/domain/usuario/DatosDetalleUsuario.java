package com.aluracursos.forohub.domain.usuario;

public record DatosDetalleUsuario(
        Long id,
        String login,
        String nombre,
        String correoElectronico) {

    public DatosDetalleUsuario(Usuario usuario) {
        this(usuario.getId(),
                usuario.getLogin(),
                usuario.getNombre(),
                usuario.getCorreoElectronico());
    }
}
