# Proyecto Backend para Force GYM Management

Este es un proyecto backend desarrollado con **Spring Boot**, utilizando **Maven**, **Spring Security**, **JWT** y **JPA** para la gestión segura de usuarios y datos.

## Requisitos previos

Se debe instalar:

- [Java 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [MySQL](https://www.mysql.com/) 

## Instalación

Clonar este repositorio y ejecutar el siguiente comando en la terminal para compilar y descargar las dependencias:

```
mvn clean install
```

## Configuración

El archivo `application.properties` debe configurarse con los siguientes parámetros:

```properties
# Configuración de proyecto
spring.application.name=una.force_gym
server.port=7000

# Configuración de la base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/dbforcegym
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

# Configuraciones de JPA
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=none 
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```
## Investigacion de exportacion de pdf y excel
https://docs.google.com/document/d/1eLLt7UZ-00EwolufuKOCpixriPweJqx-uikcLUY4csY/edit?usp=sharing

## Investigacion de librerias para la creacion de graficos
https://docs.google.com/document/d/1O431puKikcdhIpT0nF06f5LjFT9cXnDZaIZ6n65UCKM/edit?usp=sharing

## Ejecución del proyecto

Para iniciar el servidor local, usar el siguiente comando:

```
mvn spring-boot:run
```

Esto levantará la API en `http://localhost:8080/` (por defecto).

## Endpoints principales

El backend ofrece autenticación basada en JWT y gestión de usuarios. El endpoint para iniciar sesión es:

- `POST /login` - Autenticación de usuarios (devuelve un objeto que incluye el JWT)

## Seguridad y Autenticación

Este proyecto usa **Spring Security** con **JWT** para proteger los endpoints. Cada solicitud a endpoints protegidos debe incluir un token JWT en el encabezado:

```
Authorization: Bearer <TOKEN>
```

## Tecnologías utilizadas

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [Spring Security](https://spring.io/projects/spring-security)
- [JWT](https://jwt.io/)
- [JPA (Hibernate)](https://spring.io/projects/spring-data-jpa)
- [MySQL](https://www.mysql.com/)

## Documentación

Se encuentra una carpeta llamada Scrum con documentación del proceso de desarrollo, como lo son las **minutas**.

---

Hecho con Spring Boot.
Por:
- [Gerald Calderón Castillo](https://www.linkedin.com/in/gerald-calder%C3%B3n-castillo-38964627a/)
- [Jamyr González García](https://www.linkedin.com/in/jamyr-gonz%C3%A1lez-garc%C3%ADa-96ba18309/)
- [Jordi Rivas Obando](#)
- [Kevin Venegas Bermúdez](https://www.linkedin.com/in/kevin-venegas-berm%C3%BAdez-22b314239/) 