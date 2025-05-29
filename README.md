# tfg_backend

Este backend forma parte del proyecto de plataforma de telemedicina. Ha sido desarrollado con Spring Boot y utiliza una base de datos MySQL. A continuación, se detallan los pasos necesarios para levantar el backend en local.

## Requisitos previos
Java 17+

Maven

MySQL Server (8.0+ recomendado)

Postman (opcional, para pruebas de endpoints)

## Configuración de la BBDD

Crear la base de datos en MySQL

Antes de iniciar la aplicación, es necesario crear la base de datos manualmente, ya que Spring Boot no la crea automáticamente.

Abre MySQL Workbench, línea de comandos o cualquier cliente MySQL y ejecuta:

```sql
CREATE DATABASE IF NOT EXISTS tfgBBDD;
USE tfgBBDD;
```

Ejecutar el script completo de estructura e inserción de datos

Dentro del proyecto, en la carpeta sql, encontrarás el archivo:
```plaintext
sql/tfgBBDD_full_setup.sql
```
Este archivo contiene todas las sentencias necesarias:

CREATE TABLE para cada entidad del sistema

INSERT INTO para poblar las tablas con datos iniciales

Simplemente copia y ejecuta todo el script dentro de la base de datos tfgBBDD ya creada.

## Configuración del application.properties

Ubicación:
```plaintext
src/main/resources/application.properties
```
Este archivo conecta Spring Boot con tu base de datos. Asegúrate de ajustar:

spring.datasource.url=jdbc:mysql://localhost:3306/tfgBBDD?useSSL=false
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
Reemplaza TU_USUARIO y TU_CONTRASEÑA con tus credenciales locales de MySQL.

## Como iniciar el backend
Abre el proyecto en IntelliJ o tu IDE favorito.

Asegúrate de que la base de datos esté creada y poblada correctamente.

Ejecuta la clase principal con @SpringBootApplication.

El servidor debería levantarse en http://localhost:8080.


