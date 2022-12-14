# Clinica odontologica :hospital:

## Indice

* [Descripción del proyecto](#Descripción-del-proyecto)

* [Estado del proyecto](#Estado-del-proyecto)

* [Funcionalidades de la aplicación](#Funcionalidades-de-la-aplicación)

* [Como ejecutar el proyecto?](#Como-ejecutar-el-proyecto?)

* [Documentación](#Documentación)

* [Sonarqube](#Sonarqube)

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

## Como ejecutar el proyecto?

### Con docker :whale:

- `Pre requisito`: Tener instalado docker.
- `Solo necesitas ejecutar el siguiente comando`: docker run -p 8080:8080 katherinemvenegas/clinica-odontologica

### Con IDE :computer:

- `Pre requisitos`: Tener instalado Git y un IDE.
- `Pasos a seguir`: Clonar el proyecto, buscar el pom y abrirlo con el IDE. Por último, seleccionar Run.

*Una vez levantado el proyecto, ya podemos visualizar sus funcionalidades en el link que figura en "Documentación".*

## Documentación

En el siguiente link se puede observar el detalle de los endpoints: <a href="http://localhost:8080/swagger-ui/index.html" target="_blank">swagger</a>
- Primero ejecutar el endpoint signup
- Luego el endpoint login y agregar el jwt obtenido en authorize
- Ya podemos utilizar el resto de los endpoint dependiendo el rol asignado en signup

## Sonarqube

- `Pre requisitos`: Tener instalado docker.
- `Pasos a seguir`: docker-compose up e ingresar a <a href="http://localhost:9000" target="_blank">reporte sonarqube</a>
- `En caso de error de memoria`: wsl -d docker-desktop sysctl -w vm.max_map_count=262144 y repetir el comando para
  levantar el contenedor.

## Tecnologías utilizadas

* Java 11

* Spring Boot

* Maven

* JPA

* JUnit

* Mockito

* Swagger

* JWT

* Docker

* Sonarqube
