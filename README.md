# 💬 ForoHub API

**ForoHub** es una **API REST** desarrollada con **Java y Spring Boot** que permite gestionar un sistema de foro donde los usuarios pueden crear tópicos de discusión, realizar preguntas, compartir sugerencias y responder a otros usuarios.

El sistema implementa **autenticación y control de acceso**, permitiendo que solo los usuarios autenticados puedan crear, modificar o eliminar contenido dentro de la plataforma.

Este proyecto fue desarrollado como parte de un desafío backend enfocado en:

- Construcción de **APIs REST**
- **Autenticación de usuarios**
- **Persistencia de datos**
- Arquitectura con **Spring Boot**

# 🚀 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **Validation**
- **MySQL**
- **Maven**
- **API REST**

# 🎯 Funcionalidades

La API permite realizar las siguientes operaciones:

### 📄 Gestión de Tópicos

- Registrar un nuevo tópico
- Listar tópicos registrados
- Actualizar información de un tópico existente
- Eliminar un tópico

### 🔐 Autenticación de Usuarios

- Los usuarios deben estar **registrados previamente en la base de datos**.
- Solo los usuarios **autenticados** pueden:
    - Crear tópicos
    - Actualizar tópicos
    - Eliminar tópicos

Los usuarios que **no estén autenticados** no podrán acceder a estas operaciones.

# 🗄️ Modelo de Datos

La base de datos está diseñada para representar la estructura de un foro y está compuesta por las siguientes entidades principales:

| Entidad | Descripción |
| --- | --- |
| **usuarios** | Usuarios registrados en el sistema |
| **perfiles** | Roles o permisos asignados a los usuarios |
| **usuario_perfil** | Relación entre usuarios y perfiles |
| **topicos** | Publicaciones creadas por los usuarios |
| **respuestas** | Respuestas realizadas a los tópicos |
| **cursos** | Categorías o temas asociados a los tópicos |

Estas entidades permiten gestionar la interacción entre usuarios dentro del foro y mantener organizada la información.

# ⚙️ Configuración del Proyecto

### 1️⃣ Clonar el repositorio

Abre el proyecto en tu IDE preferido (IntelliJ, Eclipse, VS Code, etc.).

### 2️⃣ Configurar la base de datos

Crear una base de datos en MySQL:

```
CREATE DATABASE forohub;
```

### 3️⃣ Configurar conexión

Configurar el archivo **application.properties**:

```
spring.datasource.url=jdbc:mysql://localhost/forohub
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.flyway.enabled=true

api.security.token.secret=${JWT_SECRET}
```

2️⃣

### Ejecutar la aplicación

La API se ejecutará en:

```
http://localhost:8080
```

# 🔐 Seguridad

La aplicación utiliza **Spring Security** para gestionar la autenticación y autorización de usuarios.

Solo los usuarios autenticados pueden realizar operaciones que modifican los datos del sistema.

# 🔮 Posibles Mejoras Futuras

- Agregar **sistema de comentarios o respuestas anidadas**
- Implementar **roles de usuario (admin, moderador, usuario)**
- Documentar la API con **Swagger / OpenAPI**

# **👩‍💻 Autor**

Proyecto realizado por Noelia Rementeria el cual forma parte del Challenge ONE - ForoHub propuesto por Alura Latam en conjunto con Oracle dentro de la formación como principiante en Programación.

# **📜 Licencia**

Este proyecto se distribuye con fines educativos. Puedes usarlo, modificarlo y distribuirlo libremente.