-- =========================================================
-- Proyecto: ReservaHotel (Ejercicio 31)
-- Asignatura: Desarrollo Web - Unidad 1
-- Motor: MySQL / MariaDB
-- =========================================================

CREATE DATABASE IF NOT EXISTS reservahotel_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE reservahotel_db;

-- ---------------------------------------------------------
-- Tabla: usuario
-- ---------------------------------------------------------
CREATE TABLE usuario (
    id_usuario   INT AUTO_INCREMENT PRIMARY KEY,
    clave        VARCHAR(255) NOT NULL,
    nombre       VARCHAR(100) NOT NULL,
    rol          VARCHAR(30)  NOT NULL,
    email        VARCHAR(150) NOT NULL UNIQUE
);

-- ---------------------------------------------------------
-- Tabla: reserva_hotel
-- ---------------------------------------------------------
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

    CONSTRAINT fk_reserva_empleado_atiende
        FOREIGN KEY (empleado_atiende_id) REFERENCES usuario(id_usuario),
    CONSTRAINT fk_reserva_empleado_despide
        FOREIGN KEY (empleado_despide_id) REFERENCES usuario(id_usuario)
);

-- =========================================================
-- Datos iniciales
-- =========================================================

INSERT INTO usuario (clave, nombre, rol, email) VALUES
('admin123',   'Isaac Administrador', 'admin',    'admin@reservahotel.com'),
('empleado123','Laura Gómez',         'empleado', 'laura@reservahotel.com'),
('empleado456','Carlos Pérez',        'empleado', 'carlos@reservahotel.com');

INSERT INTO reserva_hotel
(fecha, hotel, huesped, fecha_inicio, fecha_fin, valor, habitacion,
 num_acompanantes, pais, departamento, ciudad, hora_checkin, hora_checkout,
 empleado_atiende_id, empleado_despide_id, descripcion)
VALUES
('2026-09-01', 'Hotel Caribe',      'Ana Torres',    '2026-09-05', '2026-09-08', 450000.00, '204', 1,
 'Colombia', 'Bolívar', 'Cartagena', '15:00:00', '11:00:00', 2, 3, 'Reserva estándar, vista al mar'),
('2026-09-03', 'Hotel Estelar',     'Jorge Ramírez', '2026-09-10', '2026-09-12', 260000.00, '101', 0,
 'Colombia', 'Atlántico', 'Barranquilla', '14:00:00', '12:00:00', 2, NULL, 'Habitación sencilla, sin acompañantes'),
('2026-09-05', 'Hotel Movich',      'Marta Ríos',    '2026-09-15', '2026-09-20', 900000.00, '502', 3,
 'Colombia', 'Bolívar', 'Cartagena', '16:00:00', '10:00:00', 3, 2, 'Suite familiar, incluye desayuno');
