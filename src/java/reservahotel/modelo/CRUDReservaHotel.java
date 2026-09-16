package reservahotel.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 * Clase CRUDReservaHotel se utiliza para agregar, modificar, eliminar,
 * consultar y listar reservas de hotel en el sistema, y para los
 * reportes parametrizados sobre esta entidad.
 */
public class CRUDReservaHotel {

    private ReservaHotel alguna;
    private ConexionBaseDatos baseDatos;

    public CRUDReservaHotel() throws Exception {
        this.alguna = new ReservaHotel();
        this.baseDatos = new ConexionBaseDatos();
    }

    public ReservaHotel getAlguna() {
        return alguna;
    }

    public void setAlguna(ReservaHotel alguna) {
        this.alguna = alguna;
    }

    public void agregarReserva() throws Exception {
        String sqlInsert = "INSERT INTO reserva_hotel "
                + "(fecha, hotel, huesped, fecha_inicio, fecha_fin, valor, habitacion, "
                + "num_acompanantes, pais, departamento, ciudad, hora_checkin, hora_checkout, "
                + "empleado_atiende_id, empleado_despide_id, descripcion) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement s = baseDatos.crearSentencia(sqlInsert);
            s.setDate(1, alguna.getFecha());
            s.setString(2, alguna.getHotel());
            s.setString(3, alguna.getHuesped());
            s.setDate(4, alguna.getFechaInicio());
            s.setDate(5, alguna.getFechaFin());
            s.setBigDecimal(6, alguna.getValor());
            s.setString(7, alguna.getHabitacion());
            s.setInt(8, alguna.getNumAcompanantes());
            s.setString(9, alguna.getPais());
            s.setString(10, alguna.getDepartamento());
            s.setString(11, alguna.getCiudad());
            s.setTime(12, alguna.getHoraCheckin());
            s.setTime(13, alguna.getHoraCheckout());
            s.setInt(14, alguna.getEmpleadoAtiendeId());
            if (alguna.getEmpleadoDespideId() != null) {
                s.setInt(15, alguna.getEmpleadoDespideId());
            } else {
                s.setNull(15, Types.INTEGER);
            }
            s.setString(16, alguna.getDescripcion());
            baseDatos.actualizar(s);
        } catch (Exception error) {
            throw new Exception("Error al Agregar la Reserva <br/>Explicacion: " + error.getMessage());
        } finally {
            baseDatos.desconectar();
        }
    }

    public void modificarReserva() throws Exception {
        if (alguna.getId() == 0) {
            throw new Exception("El ID de la Reserva es Necesario");
        }
        String sqlUpdate = "UPDATE reserva_hotel SET "
                + "fecha=?, hotel=?, huesped=?, fecha_inicio=?, fecha_fin=?, valor=?, habitacion=?, "
                + "num_acompanantes=?, pais=?, departamento=?, ciudad=?, hora_checkin=?, hora_checkout=?, "
                + "empleado_atiende_id=?, empleado_despide_id=?, descripcion=? "
                + "WHERE id_reserva=?";
        try {
            PreparedStatement s = baseDatos.crearSentencia(sqlUpdate);
            s.setDate(1, alguna.getFecha());
            s.setString(2, alguna.getHotel());
            s.setString(3, alguna.getHuesped());
            s.setDate(4, alguna.getFechaInicio());
            s.setDate(5, alguna.getFechaFin());
            s.setBigDecimal(6, alguna.getValor());
            s.setString(7, alguna.getHabitacion());
            s.setInt(8, alguna.getNumAcompanantes());
            s.setString(9, alguna.getPais());
            s.setString(10, alguna.getDepartamento());
            s.setString(11, alguna.getCiudad());
            s.setTime(12, alguna.getHoraCheckin());
            s.setTime(13, alguna.getHoraCheckout());
            s.setInt(14, alguna.getEmpleadoAtiendeId());
            if (alguna.getEmpleadoDespideId() != null) {
                s.setInt(15, alguna.getEmpleadoDespideId());
            } else {
                s.setNull(15, Types.INTEGER);
            }
            s.setString(16, alguna.getDescripcion());
            s.setInt(17, alguna.getId());
            baseDatos.actualizar(s);
        } catch (Exception error) {
            throw new Exception("Error al Actualizar la Reserva " + alguna.getId()
                    + "<br/>Explicacion: " + error.getMessage());
        } finally {
            baseDatos.desconectar();
        }
    }

    public void eliminarReserva() throws Exception {
        if (alguna.getId() == 0) {
            throw new Exception("El ID de la Reserva es Necesario");
        }
        String sqlDelete = "DELETE FROM reserva_hotel WHERE id_reserva=?";
        try {
            PreparedStatement s = baseDatos.crearSentencia(sqlDelete);
            s.setInt(1, alguna.getId());
            baseDatos.actualizar(s);
        } catch (Exception error) {
            throw new Exception("Error al Eliminar la Reserva " + alguna.getId()
                    + "<br/>Explicacion: " + error.getMessage());
        } finally {
            baseDatos.desconectar();
        }
    }

    public static ReservaHotel consultarReserva(int id) throws Exception {
        if (id == 0) {
            throw new Exception("El ID de la Reserva es Necesario");
        }
        ConexionBaseDatos baseDatos = null;
        String sqlSelect = "SELECT * FROM reserva_hotel WHERE id_reserva=?";
        try {
            baseDatos = new ConexionBaseDatos();
            PreparedStatement s = baseDatos.crearSentencia(sqlSelect);
            s.setInt(1, id);
            ResultSet r = baseDatos.consultar(s);
            if (r.next()) {
                return mapearFila(r);
            } else {
                throw new Exception("Error al Consultar la Reserva " + id);
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage() + " La Reserva No existe en la BD");
        } finally {
            if (baseDatos != null) baseDatos.desconectar();
        }
    }

    public static ReservaHotel[] listarTodasLasReservas() throws Exception {
        ConexionBaseDatos baseDatos = null;
        String sqlSelect = "SELECT * FROM reserva_hotel";
        try {
            baseDatos = new ConexionBaseDatos();
            PreparedStatement s = baseDatos.crearSentencia(sqlSelect);
            ResultSet r = baseDatos.consultar(s);
            r.last();
            ReservaHotel[] listado = new ReservaHotel[r.getRow()];
            r.beforeFirst();
            int contador = 0;
            while (r.next()) {
                listado[contador] = mapearFila(r);
                contador++;
            }
            if (listado.length <= 0) {
                throw new Exception("Error al Listar las Reservas");
            }
            return listado;
        } catch (Exception error) {
            throw new Exception(error.getMessage() + " La BD esta vacia");
        } finally {
            if (baseDatos != null) baseDatos.desconectar();
        }
    }

    // --- Reportes parametrizados (mínimo 2 requeridos por la actividad) ---

    /** Reporte 1: reservas filtradas por ciudad. */
    public static ReservaHotel[] reservasPorCiudad(String ciudad) throws Exception {
        ConexionBaseDatos baseDatos = null;
        String sql = "SELECT * FROM reserva_hotel WHERE ciudad = ?";
        try {
            baseDatos = new ConexionBaseDatos();
            PreparedStatement s = baseDatos.crearSentencia(sql);
            s.setString(1, ciudad);
            ResultSet r = baseDatos.consultar(s);
            r.last();
            ReservaHotel[] listado = new ReservaHotel[r.getRow()];
            r.beforeFirst();
            int contador = 0;
            while (r.next()) {
                listado[contador] = mapearFila(r);
                contador++;
            }
            return listado;
        } finally {
            if (baseDatos != null) baseDatos.desconectar();
        }
    }

    /** Reporte 2: reservas atendidas por un empleado específico, entre dos fechas. */
    public static ReservaHotel[] reservasPorEmpleadoYRango(int empleadoId, java.sql.Date desde, java.sql.Date hasta) throws Exception {
        ConexionBaseDatos baseDatos = null;
        String sql = "SELECT * FROM reserva_hotel WHERE empleado_atiende_id = ? "
                + "AND fecha_inicio BETWEEN ? AND ?";
        try {
            baseDatos = new ConexionBaseDatos();
            PreparedStatement s = baseDatos.crearSentencia(sql);
            s.setInt(1, empleadoId);
            s.setDate(2, desde);
            s.setDate(3, hasta);
            ResultSet r = baseDatos.consultar(s);
            r.last();
            ReservaHotel[] listado = new ReservaHotel[r.getRow()];
            r.beforeFirst();
            int contador = 0;
            while (r.next()) {
                listado[contador] = mapearFila(r);
                contador++;
            }
            return listado;
        } finally {
            if (baseDatos != null) baseDatos.desconectar();
        }
    }

    private static ReservaHotel mapearFila(ResultSet r) throws Exception {
        ReservaHotel alguna = new ReservaHotel();
        alguna.setId(r.getInt("id_reserva"));
        alguna.setFecha(r.getDate("fecha"));
        alguna.setHotel(r.getString("hotel"));
        alguna.setHuesped(r.getString("huesped"));
        alguna.setFechaInicio(r.getDate("fecha_inicio"));
        alguna.setFechaFin(r.getDate("fecha_fin"));
        alguna.setValor(r.getBigDecimal("valor"));
        alguna.setHabitacion(r.getString("habitacion"));
        alguna.setNumAcompanantes(r.getInt("num_acompanantes"));
        alguna.setPais(r.getString("pais"));
        alguna.setDepartamento(r.getString("departamento"));
        alguna.setCiudad(r.getString("ciudad"));
        alguna.setHoraCheckin(r.getTime("hora_checkin"));
        alguna.setHoraCheckout(r.getTime("hora_checkout"));
        alguna.setEmpleadoAtiendeId(r.getInt("empleado_atiende_id"));
        int despideId = r.getInt("empleado_despide_id");
        alguna.setEmpleadoDespideId(r.wasNull() ? null : despideId);
        alguna.setDescripcion(r.getString("descripcion"));
        return alguna;
    }
}
