package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.curso.CursoRepository;
import com.aluracursos.forohub.domain.topico.*;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public TopicoController(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriBuilder) {
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            return ResponseEntity.badRequest().body("ERROR. Ya existe un tópico con ese título y mensaje. ");
        }

        var autor = usuarioRepository.findByNombre(datos.autor());
        var curso = cursoRepository.findByNombre(datos.curso());
        var topico = new Topico(datos, autor, curso);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer año,
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {

        Page<Topico> pagina;
        if (curso != null && año != null) {
            pagina = topicoRepository.findByCursoyAño(curso, año, paginacion);
        } else {
            pagina = topicoRepository.findAllByActivoTrue(paginacion);
        }

        return ResponseEntity.ok(pagina.map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(topico -> ResponseEntity.ok(new DatosDetalleTopico(topico)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizadosTopico datos) {
        var topicoOpcional = topicoRepository.findById(id);
        if (topicoOpcional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topico = topicoOpcional.get();
        if (!topico.getTitulo().equals(datos.titulo()) || !topico.getMensaje().equals(datos.mensaje())) {
            return ResponseEntity.badRequest().body("ERROR. Ya existe un tópico con ese título y mensaje. ");
        }

        topico.actualizarDatos(datos);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) {
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        var topico = topicoRepository.getReferenceById(id);
        topico.eliminar();

        return ResponseEntity.noContent().build();
    }
}
