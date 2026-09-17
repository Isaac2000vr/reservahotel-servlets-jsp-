# Sistema de Reserva de Hotel (Servlets/JSP + JDBC)

Proyecto de la actividad de Servlets/JSP para la asignatura Desarrollo Web (Unidad 1). Me correspondió el ejercicio N.º 31: **ReservaHotel**.

La aplicación está hecha con Servlet/JSP puro y acceso a datos manual con JDBC (sin frameworks MVC ni ORM), siguiendo la guía "CRUD JSP + JDBC" proporcionada por el docente.

## Datos de la actividad

- Asignatura: Desarrollo Web — Unidad 1
- Ejercicio asignado: N.º 31 — ReservaHotel
- Entidades: Usuario (obligatoria) y ReservaHotel (asignada)
- Servidor: Apache Tomcat 9
- Base de datos: MySQL

## Estructura del proyecto

src/java/reservahotel/
├── modelo/
│ ├── Usuario.java
│ ├── ReservaHotel.java
│ ├── ConexionBaseDatos.java -> maneja la conexión JDBC
│ ├── CRUDUsuario.java -> CRUD, login y reportes de Usuario
│ ├── CRUDReservaHotel.java -> CRUD y reportes de ReservaHotel
│ └── ServicioEmail.java -> envío de correo para recuperación de clave
└── controladores/
├── ServletUsuario.java
└── ServletReservaHotel.java


En `web/` están las vistas JSP, organizadas en `usuario/` y `reservahotel/`, cada una con `agregar.jsp`, `buscar.jsp`, `modificar.jsp`, `eliminar.jsp` y `listar.jsp`. También está `login.jsp`, `recuperar.jsp`, `index.jsp` (menú principal, protegido por sesión) y `mensaje.jsp` (para mostrar mensajes de error/éxito).

## Base de datos

El script completo de creación de la base de datos (tablas + datos de prueba) está en `script_creacion_bd.sql`, en la raíz del proyecto.

Resumen del esquema:

```sql
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
    FOREIGN KEY (empleado_atiende_id) REFERENCES usuario(id_usuario),
    FOREIGN KEY (empleado_despide_id) REFERENCES usuario(id_usuario)
);
```

Usuarios de prueba:

| Email | Clave | Rol |
|---|---|---|
| admin@reservahotel.com | admin123 | admin |
| laura@reservahotel.com | empleado123 | empleado |
| carlos@reservahotel.com | empleado456 | empleado |

## Reportes parametrizados

- Usuario: por rol, y por nombre (búsqueda parcial con LIKE)
- ReservaHotel: por ciudad, y por empleado + rango de fechas

## Otras funcionalidades

- Recuperación de contraseña por correo (genera una clave temporal y la envía por email)
- Protección de rutas: si no hay sesión activa, redirige al login
- Los `ResultSet` se crean como `TYPE_SCROLL_INSENSITIVE` / `CONCUR_READ_ONLY`, necesario para poder usar `.last()` / `.beforeFirst()` al armar los listados

## Cómo correrlo en local

1. Clonar el repo:

git clone https://github.com/Isaac2000vr/reservahotel-servlets-jsp-.git

2. Tener MySQL corriendo (yo uso XAMPP).
3. Crear la base ejecutando `script_creacion_bd.sql`.
4. Abrir el proyecto en NetBeans, Clean and Build, y correrlo con Apache Tomcat 9.
5. Entrar a `http://localhost:8080/reservahotel-servlets-jsp/`

Por defecto, si no hay variables de entorno configuradas, la app se conecta a `localhost:3306` con usuario `root` sin contraseña (la config típica de XAMPP).

## Despliegue

La app está desplegada usando Docker:

- **App:** Render (servicio web basado en Docker, con Tomcat 9 corriendo el `.war`)
- **Base de datos:** Clever Cloud (addon de MySQL)

URL: https://reservahotel-servlets-jsp.onrender.com/

La conexión a la base de datos en producción se configura con variables de entorno (en vez de tenerlas escritas en el código), leídas en `ConexionBaseDatos.java`:

| Variable | Descripción |
|---|---|
| `DB_HOST` | Host del servidor MySQL |
| `DB_PORT` | Puerto (normalmente 3306) |
| `DB_USER` | Usuario de la base de datos |
| `DB_PASSWORD` | Contraseña |
| `DB_NAME` | Nombre de la base de datos |

Si no se define ninguna, usa los valores de desarrollo local por defecto (localhost/root/sin clave).

**Nota:** el plan gratuito de Render "duerme" el servicio tras un rato sin tráfico, así que la primera vez que se abre el enlace después de estar inactivo puede tardar unos 30-60 segundos en cargar.