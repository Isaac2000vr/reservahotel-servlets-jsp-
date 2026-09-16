# Sistema de Reserva de Hotel — Servlets / JSP + JDBC

Este repositorio contiene la implementación completa de la **Unidad 1** para la asignatura de **Desarrollo Web**. Corresponde al **Ejercicio N.° 31 (ReservaHotel)**, desarrollado bajo la arquitectura clásica **JSP + Java Servlets + JDBC manual** (sin frameworks MVC externos ni ORMs).

---

## 📋 Información de la Actividad

- **Asignatura:** Desarrollo Web — Unidad 1
- **Ejercicio Asignado:** N.º 31 — ReservaHotel
- **Entidades:** `Usuario` (Común obligatoria) y `ReservaHotel` (Específica asignada)
- **Servidor Web:** Apache Tomcat 9 (`javax.servlet.*`)
- **Base de Datos:** MySQL / MariaDB (XAMPP)

---

## 🏛️ Arquitectura del Proyecto

El proyecto sigue estrictamente el patrón arquitectónico guiado en clase:

```
src/java/reservahotel/
├── modelo/
│   ├── Usuario.java              (Entidad de usuario: id, clave, nombre, rol, email)
│   ├── ReservaHotel.java         (Entidad de reserva hotelera: 17 atributos)
│   ├── ConexionBaseDatos.java    (Gestor de conexión JDBC y PreparedStatements)
│   ├── CRUDUsuario.java          (Operaciones CRUD, inicio de sesión, reportes y recuperación)
│   ├── CRUDReservaHotel.java     (Operaciones CRUD y reportes de reservas)
│   └── ServicioEmail.java        (Gestor de notificaciones por correo vía SMTP/javax.mail)
└── controladores/
    ├── ServletUsuario.java       (Controlador de acciones para Usuario)
    └── ServletReservaHotel.java  (Controlador de acciones para ReservaHotel)
```

### Rutas y Vistas JSP (`web/`)
- `index.jsp`: Menú principal protegido con guard de sesión.
- `mensaje.jsp`: Vista genérica para notificaciones y mensajes de error.
- `usuario/`: `login.jsp`, `agregar.jsp`, `buscar.jsp`, `modificar.jsp`, `eliminar.jsp`, `listar.jsp`, `recuperar.jsp`.
- `reservahotel/`: `agregar.jsp`, `buscar.jsp`, `modificar.jsp`, `eliminar.jsp`, `listar.jsp`.

---

## 🗄️ Esquema de Base de Datos (`reservahotel_db`)

```sql
CREATE DATABASE IF NOT EXISTS reservahotel_db;
USE reservahotel_db;

CREATE TABLE usuario (
    id_usuario   INT AUTO_INCREMENT PRIMARY KEY,
    clave        VARCHAR(255) NOT NULL,
    nombre       VARCHAR(100) NOT NULL,
    rol          VARCHAR(30)  NOT NULL,
    email        VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE reserva_hotel (
    id_reserva          INT AUTO_INCREMENT PRIMARY KEY,
    fecha               DATE NOT NULL,
    hotel               VARCHAR(150) NOT NULL,
    huesped             VARCHAR(150) NOT NULL,
    fecha_inicio        DATE NOT NULL,
    fecha_fin           DATE NOT NULL,
    valor               DECIMAL(12,2) NOT NULL,
    habitacion          VARCHAR(20)  NOT NULL,
    num_acompanantes    INT NOT NULL DEFAULT 0,
    pais                VARCHAR(80)  NOT NULL,
    departamento        VARCHAR(80)  NOT NULL,
    ciudad              VARCHAR(80)  NOT NULL,
    hora_checkin        TIME NOT NULL,
    hora_checkout       TIME NOT NULL,
    empleado_atiende_id INT NOT NULL,
    empleado_despide_id INT NULL,
    descripcion         VARCHAR(500),
    CONSTRAINT fk_reserva_empleado_atiende FOREIGN KEY (empleado_atiende_id) REFERENCES usuario(id_usuario),
    CONSTRAINT fk_reserva_empleado_despide FOREIGN KEY (empleado_despide_id) REFERENCES usuario(id_usuario)
);
```

### Datos de Prueba (Usuarios)

| ID | Clave | Nombre | Rol | Email |
|---|---|---|---|---|
| 15 | admin123 | Isaac Administrador | admin | admin@reservahotel.com |
| 16 | empleado123 | Laura Gómez | empleado | laura@reservahotel.com |
| 17 | empleado456 | Carlos Pérez | empleado | carlos@reservahotel.com |

---

## 📊 Reportes Parametrizados Implementados

Se incorporaron **4 reportes parametrizados** (2 por cada entidad) para cumplir con las exigencias del taller:

1. **Reservas por Ciudad:** Filtra reservas según la ciudad especificada por el usuario.
2. **Reservas por Empleado y Rango de Fechas:** Lista reservas atendidas por un empleado específico dentro de una ventana de fechas (`fecha_inicio BETWEEN ? AND ?`).
3. **Usuarios por Rol:** Filtra usuarios según su rol (`admin` o `empleado`).
4. **Usuarios por Nombre:** Búsqueda parcial de usuarios utilizando coincidencia `LIKE %nombre%`.

---

## 🔑 Funcionalidades Adicionales

- **Recuperación de Contraseña:** Generación automática de clave temporal y envío por correo mediante `ServicioEmail` / `javax.mail`.
- **Protección de Rutas (Guard):** Verificación de sesión activa en `index.jsp` y las vistas hijas.
- **Scrollable ResultSets:** PreparedStatements configurados con `TYPE_SCROLL_INSENSITIVE` y `CONCUR_READ_ONLY` para conteo dinámico de filas.

---

## 🚀 Instrucciones de Ejecución

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Isaac2000vr/reservahotel-servlets-jsp-.git
   ```
2. Iniciar el servicio **MySQL** en XAMPP.
3. Crear la base de datos `reservahotel_db` e importar el esquema anterior.
4. Abrir el proyecto en **NetBeans IDE** (versión 12 o superior).
5. Realizar **Clean and Build** y presionar **Run** con **Apache Tomcat 9**.
6. Acceder en el navegador a `http://localhost:8080/reservahotel-servlets-jsp/` (o el puerto configurado).