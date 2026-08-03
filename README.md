# Tesco Control API

API REST para el sistema de control de acceso a laboratorios de cómputo del
Tecnológico de Coacalco mediante tarjetas NFC. Gestiona maestros, tarjetas,
laboratorios, materias y horarios, valida cada intento de acceso contra el
horario programado y lleva una bitácora de todos los intentos.

Frontend complementario: [`tesco-control-web`](https://github.com/hdaniel-espinosa/tesco-control-web).

## Stack

- Java 21
- Spring Boot 4.1 (Web, Data JPA, Security)
- MySQL (vía `mysql-connector-j`) + Hibernate
- Lombok
- springdoc-openapi (Swagger UI)

## Puesta en marcha

### 1. Base de datos

Requiere un servidor MySQL/MariaDB accesible en `localhost:3306`. Ejecuta los
scripts de `resources/scripts/` en orden:

```bash
mysql -uroot < resources/scripts/1-creacion-base-datos.sql   # crea la base `controlpuertas`
mysql -uroot < resources/scripts/2-creacion-usuario.sql      # crea el usuario controlpuertasusr
mysql -uroot < resources/scripts/3-creacion-tablas.sql       # crea las tablas
```

Si la base ya existía antes de que las columnas tuvieran `AUTO_INCREMENT`,
aplica también la migración:

```bash
mysql -uroot controlpuertas < resources/scripts/4-migracion-auto-increment.sql
```

Las credenciales (`controlpuertasusr` / `controltesco123`) y el nombre de la
base (`controlpuertas`) están fijos en `src/main/resources/application.properties`.

### 2. Levantar la API

```bash
./mvnw spring-boot:run
```

La API queda disponible en `http://localhost:48080/tesco-control-api`. La
documentación interactiva (Swagger UI) está en `http://localhost:48080/swagger-ui.html`.

### 3. Pruebas

```bash
./mvnw test
```

## Autenticación

La API usa HTTP Basic. El único usuario configurado (en memoria, ver
`WebSecurityConfig`) es:

- **Usuario:** `user`
- **Contraseña:** `password`

Dos endpoints quedan abiertos sin autenticación porque los invoca hardware
sin sesión de usuario:

- `POST /tesco-control-api/acceso/validar` (el lector NFC de la puerta)
- `POST /tesco-control-api/estados-laboratorio` (los sensores de
  temperatura/humedad)

Todo lo demás exige `Authorization: Basic ...`.

## Modelo de datos

| Entidad | Descripción |
|---|---|
| `Laboratorio` | Laboratorio de cómputo (nombre, edificio, lugares). |
| `EstadoLaboratorio` | Última lectura de temperatura/humedad de un laboratorio. |
| `Materia` | Materia impartida (nombre, grupo). |
| `Usuario` | Maestro (nombre, contacto, activo/inactivo). |
| `UsuarioMateria` | Qué materias imparte cada maestro (N:M). |
| `Tarjeta` | Tarjeta NFC (id, tipo, activa/inactiva). |
| `UsuarioTarjeta` | Qué tarjeta tiene asignada cada maestro. |
| `Horario` | Día/hora en que una materia se imparte en un laboratorio. |
| `Registro` | Bitácora: cada intento de acceso (concedido o denegado). |

## Lógica de acceso

`AccesoService` decide si una tarjeta abre un laboratorio evaluando, en
orden: que la tarjeta exista y esté activa, que esté asignada a un maestro
activo, que ese maestro imparta alguna materia con horario programado en ese
laboratorio para el día actual, y que la hora actual caiga dentro de
`[hora_inicio − 10 min, hora_termino + 10 min]`. Cada intento (concedido o
denegado) se registra en `registro`.

Para poder probar ese flujo sin depender de la hora real, `POST
/acceso/validar` acepta un campo opcional `fechaHoraSimulada`: si se envía,
se usa en lugar de `LocalDateTime.now()`. El lector NFC real nunca lo manda;
solo lo usa el simulador del frontend.

## Endpoints principales

Todos bajo `/tesco-control-api`.

**Acceso NFC**
- `POST /acceso/validar` — `{idTarjeta, idLaboratorio, fechaHoraSimulada?}` → concede o deniega el acceso.

**Dashboard**
- `GET /dashboard/laboratorios` — estado de ocupación actual de cada laboratorio (según el horario programado), con la clase actual o la próxima del día.
- `GET /dashboard/horarios-proximos?limite=5` — horarios de hoy que todavía no comienzan, ordenados por cercanía a la hora actual.

**Laboratorios**
- CRUD en `/laboratorios`.
- `GET /laboratorios/{id}/estado` — última lectura de sensores.
- `GET /laboratorios/{id}/horarios`, `GET /laboratorios/{id}/registros`.
- `POST /estados-laboratorio` — recibe lecturas de los sensores (sin autenticación).

**Maestros (`usuario`)**
- CRUD en `/usuarios`.
- `GET /usuarios/{id}/tarjetas`, `GET /usuarios/{id}/materias`, `GET /usuarios/{id}/horarios`.
- `POST`/`DELETE /usuarios/{id}/materias/{idMateria}` — asignar/desasignar materia.

**Tarjetas**
- CRUD en `/tarjetas`.
- `POST /tarjetas/{id}/asignar/{idUsuario}`, `DELETE /tarjetas/{id}/asignar` — asignar/desasignar a un maestro.
- `GET /tarjetas/{id}/registros`.

**Materias**
- CRUD en `/materias`.
- `GET /materias/{id}/maestros` — maestro(s) que la imparten.

**Horarios**
- CRUD en `/horarios`.

**Bitácora**
- `GET /registros`, `GET /laboratorios/{id}/registros`, `GET /tarjetas/{id}/registros` — incluyen `nombreMaestro` (dueño de la tarjeta al momento de la consulta, o `null` si no está asignada).

**Auth**
- `GET /auth/me` — devuelve el usuario autenticado; usado por el frontend para validar credenciales de login.

## Notas

- Endurecer la autenticación del lector NFC y de los sensores (hoy
  `permitAll`) con una API key o similar antes de exponer la API fuera de
  una red confiable.
