alter table topicos drop column autor;
alter table topicos drop column curso;

alter table topicos add autor_id bigint not null;
alter table topicos add curso_id bigint not null;

alter table topicos add constraint fk_topicos_autor_id foreign key (autor_id) references usuarios(id);
alter table topicos add constraint fk_topicos_curso_id foreign key (curso_id) references cursos(id);