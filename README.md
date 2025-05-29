**# tfg_backend

This backend is part of a telemedicine platform project. It has been developed using Spring Boot and uses a MySQL database. Below are the necessary steps to set up the backend locally.

## Pre-requisites
Java 17+

Maven

MySQL Server (8.0+ recommended)

Postman (optional, for testing endpoints)

## DataBase Setup

1. Create the Database in MySQL
   Before starting the application, the database must be created manually, as Spring Boot does not create it automatically.

Open MySQL Workbench, terminal, or any MySQL client and run:

```sql
CREATE DATABASE IF NOT EXISTS tfgBBDD;
USE tfgBBDD;
```

2. Execute the Full Setup Script

Inside the project, in the sql folder, you'll find the file:
```plaintext
sql/tfgBBDD_full_setup.sql
```
This file contains all necessary SQL statements:

CREATE TABLE statements for each system entity

INSERT INTO statements to populate tables with initial data

Simply copy and run the entire script within the previously created tfgBBDD database.

## Configuring application.properties

Location:
```plaintext
src/main/resources/application.properties
```
This file connects Spring Boot to your MySQL database. Make sure to update the following:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tfgBBDD?useSSL=false
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
```
Replace YOUR_USERNAME and YOUR_PASSWORD with your local MySQL credentials.

## How to Start the Backend
1. Open the project in IntelliJ or your preferred IDE.

2. Ensure the database is created and populated correctly.

3. Run the main class annotated with @SpringBootApplication.

4. The server should start at http://localhost:8080.

## Documentación Swagger

Once the application is running, developers can access the interactive Swagger UI to explore and test available endpoints by navigating to:
http://localhost:8080/docs/swagger-ui/index.html?url=/assets/swagger.yaml

This documentation allows you to make requests directly from the browser and helps you understand the available routes, their methods, and the expected inputs and outputs.



