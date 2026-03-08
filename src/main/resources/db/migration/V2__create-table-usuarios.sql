create table usuarios (
    id bigint not null auto_increment,
    login varchar(100) not null unique,
    contraseña varchar(300) not null,
    nombre varchar (100),
    correo_electronico varchar (100),

    primary key (id)
);