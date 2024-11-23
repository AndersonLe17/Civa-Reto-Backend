# Civa-Reto-Backend

Reto tecnico para Practicante Desarrollador FullStack

## Tech Stack

**Server:** Java, Spring Boot, PostgreSQL

## Ejecución local

Clonar el proyecto

```bash
  git clone https://github.com/AndersonLe17/Civa-Reto-Backend.git
```

Ir al directorio del proyecto

```bash
  cd Civa-Reto-Backend
```

Configurar application.yml

```bash
  src/main/resources/application.yml
```

#### Variables de entorno

Para ejecutar este proyecto, deberá agregar las siguientes variables de entorno

`JWT_SECRET`

`DB_URL`

`DB_USERNAME`

`DB_PASSWORD`

Crear la base de datos en PostgreSQL

```bash
  CREATE DATABASE civa-buses;
```

Iniciar el servidor

```bash
  mvn spring-boot:run
```

Al ejecutar el servidor se ejecutaran los scritps ubicados en el siguiente directorio, los cuales inicializan las tablas
y registros de la base de datos

```bash
  src/main/resources/db/migration
```

## API Reference

#### Login

```http
  POST /login
```

| Parameter | Type     | Description                       |
|:----------|:---------|:----------------------------------|
| `username`| `string` | **Required**. Usuario              |
| `password`| `string` | **Required**. Contraseña           |

El login se hace atravez de Authorization Basic:

Usuarios:

| Usuario     | Contraseña | Rol                                              |
|:------------|:-----------|:-------------------------------------------------|
| `ACiva`     | `admin`    | `Administrador: Todos los permisos`              |
| `OCiva`     | `operador` | `Operador: Todos los permisos`                   |
| `UInvitado` | `invitado` | `Invitado: Sin permiso en el Obtener bus por id` |


#### Listar todos los buses

```http
  GET /bus
```

#### Obtener bus por id

```http
  GET /bus/${id}
```

| Parameter | Type     | Description              |
|:----------|:---------|:-------------------------|
| `id`      | `string` | **Required**. Id del bus |

## Authors

- [@AndersonLe17](https://github.com/AndersonLe17)

