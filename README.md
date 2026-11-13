# 📚 TP Integrador · DAW1 — Sistema de Biblioteca

**Alumno:** Mansilla, Marcelino · **Legajo:** ######  
**Enunciado elegido:** biblioteca

## 📝 Descripción
API REST para gestionar el catálogo, los socios y el control de préstamos de una biblioteca.

## ⚙️ Cómo ejecutarlo
Requisitos: Java 21 (Maven viene incluido con `mvnw`).

```bash
./mvnw spring-boot:run
```
La API queda en `http://localhost:8080/api`. La base H2 se guarda en `data/` (no se sube al repositorio).  
Consola H2: `http://localhost:8080/h2-console`.

## 🏛️ Modelo
* **Libro:** id, titulo, autor, isbn.
* **Socio:** id, nombre, email.
* **Préstamo:** id, estado, fechaDevolucion. (N a 1 con Libro y Socio).
* **Estados de Préstamo:** ACTIVO → VENCIDO | DEVUELTO (Estado final).

## 🔌 Endpoints

| Método | Ruta | Respuestas |
|---|---|---|
| GET | /api/libros | 200 |
| GET | /api/libros/{id} | 200, 404 |
| POST | /api/libros | 201, 400, 409 |
| PUT | /api/libros/{id} | 200, 400, 404 |
| DELETE | /api/libros/{id} | 204, 404 |
| GET | /api/socios | 200 |
| GET | /api/socios/{id} | 200, 404 |
| POST | /api/socios | 201, 400, 409 |
| PUT | /api/socios/{id} | 200, 400, 404 |
| DELETE | /api/socios/{id} | 204, 404 |
| GET | /api/socios/{id}/prestamos | 200, 404 |
| GET | /api/prestamos | 200 |
| GET | /api/prestamos?estado={estado} | 200 |
| GET | /api/prestamos/{id} | 200, 404 |
| POST | /api/prestamos | 201, 400, 404, 409 |
| PATCH | /api/prestamos/{id}/estado | 200, 400, 404, 409 |
| DELETE | /api/prestamos/{id} | 204, 404 |

## ⚖️ Reglas de negocio
1. Crear libro con ISBN repetido → HTTP 409
2. Crear socio con email repetido → HTTP 409
3. Prestar un libro que ya se encuentra prestado → HTTP 409
4. Prestar a un socio que ya tiene 3 préstamos sin devolver → HTTP 409
5. Cambiar el estado desde DEVUELTO (transición inválida) → HTTP 409

## 🧪 Pruebas
En `test.http` están todas las peticiones de ejemplo.
