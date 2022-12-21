# Clinica odontologica :hospital:

## Indice

* [Descripción del proyecto](#Descripción-del-proyecto)

* [Estado del proyecto](#Estado-del-proyecto)

* [Funcionalidades de la aplicación](#Funcionalidades-de-la-aplicación)

* [Documentación](#Documentación)

* [Tecnologías utilizadas](#Tecnologías-utilizadas)

## Descripción del proyecto

Es un sistema de gestión de turnos, el cual permite el ABM de pacientes, odontólogos y turnos.

## Estado del proyecto

El proyecto se encuentra en desarrollo :wrench:

## Funcionalidades de la aplicación

### Paciente :face_with_thermometer:

- `Alta de paciente`: Puede registrarse en nuestra aplicación.
- `Actualizar datos`: Puede modificar sus datos, por ejemplo: actualizar domicilio.
- `Mostrar datos`: Puede ver su perfil con sus datos de registro.
- `Baja de paciente`: Puede solicitar la baja de nuestra aplicación.
- `Buscar pacientes por apellido`: El personal de la clinica puede buscar pacientes por nombre o apellido.

### Odontologo :tooth:

- `Buscar odontologo por matricula`: El personal de la clinica puede buscar al odontologo por matrícula.

### Turno :calendar:

- `Agendar turno`: Se puede agendar turno según disponiblidad del odontólogo.
- `Mostrar turnos por fecha`: Muestra los turnos por fecha, ordenados por odontologo y hora de atención.

## Documentación

En el siguiente link se puede observar el detalle de los endpoints: http://localhost:8080/swagger-ui/index.html

## Tecnologías utilizadas

* Java 11

* Spring Boot

* Maven

* JPA

* JUnit

* Mockito

* Swagger

* JWT 
