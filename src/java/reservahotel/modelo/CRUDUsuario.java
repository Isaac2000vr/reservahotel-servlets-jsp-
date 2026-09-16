package reservahotel.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase CRUDUsuario se utiliza para agregar, modificar, eliminar,
 * consultar, listar y loguear (iniciar sesión) usuarios en el sistema.
 */
public class CRUDUsuario {

    // propiedades
    private Usuario alguien;
    private ConexionBaseDatos baseDatos;

    public CRUDUsuario() throws Exception {
        this.alguien = new Usuario();
        this.baseDatos = new ConexionBaseDatos();
    }

    public Usuario getAlguien() {
        return alguien;
    }

    public void setAlguien(Usuario alguien) {
        this.alguien = alguien;
    }

    public void agregarUsuario() throws Exception {
        // armar el SQL INSERT de forma dinamica
        String sqlInsert = "INSERT INTO usuario "
                + "(clave, nombre, rol, email) "
                + "VALUES (?,?,?,?)";
        try {
            // Crear una sentencia JDBC mediante la sentencia SQL anterior
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlInsert);
            // Pasarle los datos del usuario a la sentencia SQL
            sentenciaSQL.setString(1, alguien.getClave());
            sentenciaSQL.setString(2, alguien.getNombre());
            sentenciaSQL.setString(3, alguien.getRol());
            sentenciaSQL.setString(4, alguien.getEmail());
            // actualizar la BD usando la sentenciaSQL con los datos del usuario
            baseDatos.actualizar(sentenciaSQL);
        } catch (Exception error) {
            throw new Exception("Error al Agregar el Usuario "
                    + "<br/>Explicacion: " + error.getMessage());
        } finally {
            baseDatos.desconectar();
        }
    }

    public void modificarUsuario() throws Exception {
        if (alguien.getId() == 0) {
            throw new Exception("El ID del Usuario es Necesario");
        }
        // armar el SQL UPDATE de forma dinamica
        String sqlUpdate = "UPDATE usuario "
                + "SET clave=?, nombre=?, rol=?, email=? "
                + "WHERE id_usuario=?";
        try {
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlUpdate);
            sentenciaSQL.setString(1, alguien.getClave());
            sentenciaSQL.setString(2, alguien.getNombre());
            sentenciaSQL.setString(3, alguien.getRol());
            sentenciaSQL.setString(4, alguien.getEmail());
            sentenciaSQL.setInt(5, alguien.getId());
            baseDatos.actualizar(sentenciaSQL);
        } catch (Exception error) {
            throw new Exception("Error al Actualizar el Usuario " + alguien.getId()
                    + "<br/>Explicacion: " + error.getMessage());
        } finally {
            baseDatos.desconectar();
        }
    }

    public void eliminarUsuario() throws Exception {
        if (alguien.getId() == 0) {
            throw new Exception("El ID del Usuario es Necesario");
        }
        // armar el SQL DELETE de forma dinamica
        String sqlDelete = "DELETE FROM usuario WHERE id_usuario=?";
        try {
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlDelete);
            sentenciaSQL.setInt(1, alguien.getId());
            baseDatos.actualizar(sentenciaSQL);
        } catch (Exception error) {
            throw new Exception("Error al Eliminar el Usuario " + alguien.getId()
                    + "<br/>Explicacion: " + error.getMessage());
        } finally {
            baseDatos.desconectar();
        }
    }

    public static Usuario iniciarSesion(String email, String password) throws Exception {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new Exception("El Email y la Clave del Usuario son Necesarios");
        }
        Usuario alguien;
        ConexionBaseDatos baseDatos = null;
        // armar el SQL SELECT de forma dinamica
        String sqlSelect = "SELECT * FROM usuario WHERE email=? AND clave=?";
        try {
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlSelect);
            sentenciaSQL.setString(1, email);
            sentenciaSQL.setString(2, password);
            ResultSet resultado = baseDatos.consultar(sentenciaSQL);
            if (resultado.next()) {
                alguien = new Usuario();
                alguien.setId(resultado.getInt("id_usuario"));
                alguien.setClave(resultado.getString("clave"));
                alguien.setNombre(resultado.getString("nombre"));
                alguien.setRol(resultado.getString("rol"));
                alguien.setEmail(resultado.getString("email"));
                return alguien;
            } else {
                throw new Exception("Error al Iniciar Sesion, datos incorrectos");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage() + " El Email o la Clave estan Errados");
        } finally {
            if (baseDatos != null) {
                baseDatos.desconectar();
            }
        }
    }

    public static Usuario consultarUsuario(int id) throws Exception {
        if (id == 0) {
            throw new Exception("El ID del Usuario es Necesario");
        }
        Usuario alguien;
        ConexionBaseDatos baseDatos = null;
        String sqlSelect = "SELECT * FROM usuario WHERE id_usuario=?";
        try {
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlSelect);
            sentenciaSQL.setInt(1, id);
            ResultSet resultado = baseDatos.consultar(sentenciaSQL);
            if (resultado.next()) {
                alguien = new Usuario();
                alguien.setId(resultado.getInt("id_usuario"));
                alguien.setClave(resultado.getString("clave"));
                alguien.setNombre(resultado.getString("nombre"));
                alguien.setRol(resultado.getString("rol"));
                alguien.setEmail(resultado.getString("email"));
                return alguien;
            } else {
                throw new Exception("Error al Consultar el Usuario " + id);
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage() + " El usuario No existe en la BD");
        } finally {
            if (baseDatos != null) {
                baseDatos.desconectar();
            }
        }
    }

    public static Usuario[] listarTodosLosUsuarios() throws Exception {
        Usuario alguien;
        ConexionBaseDatos baseDatos = null;
        String sqlSelect = "SELECT * FROM usuario";
        try {
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlSelect);
            ResultSet resultado = baseDatos.consultar(sentenciaSQL);
            resultado.last(); // colocarnos en el ultimo registro del resultado
            Usuario[] listado = new Usuario[resultado.getRow()];
            resultado.beforeFirst(); // nos colocamos antes del primer registro
            int contador = 0;
            while (resultado.next()) {
                alguien = new Usuario();
                alguien.setId(resultado.getInt("id_usuario"));
                alguien.setClave(resultado.getString("clave"));
                alguien.setNombre(resultado.getString("nombre"));
                alguien.setRol(resultado.getString("rol"));
                alguien.setEmail(resultado.getString("email"));
                listado[contador] = alguien;
                contador++;
            }
            if (listado.length <= 0) {
                throw new Exception("Error al Listar los Usuarios");
            }
            return listado;
        } catch (Exception error) {
            throw new Exception(error.getMessage() + " La BD esta vacia");
        } finally {
            if (baseDatos != null) {
                baseDatos.desconectar();
            }
        }
    }

    // --- Reportes parametrizados (mínimo 2 requeridos por la actividad) ---

    /** Reporte 1: usuarios filtrados por rol. */
    public static Usuario[] usuariosPorRol(String rol) throws Exception {
        ConexionBaseDatos baseDatos = null;
        String sql = "SELECT * FROM usuario WHERE rol = ?";
        try {
            baseDatos = new ConexionBaseDatos();
            PreparedStatement s = baseDatos.crearSentencia(sql);
            s.setString(1, rol);
            ResultSet r = baseDatos.consultar(s);
            r.last();
            Usuario[] listado = new Usuario[r.getRow()];
            r.beforeFirst();
            int contador = 0;
            while (r.next()) {
                Usuario alguien = new Usuario();
                alguien.setId(r.getInt("id_usuario"));
                alguien.setClave(r.getString("clave"));
                alguien.setNombre(r.getString("nombre"));
                alguien.setRol(r.getString("rol"));
                alguien.setEmail(r.getString("email"));
                listado[contador] = alguien;
                contador++;
            }
            return listado;
        } finally {
            if (baseDatos != null) baseDatos.desconectar();
        }
    }

    /** Reporte 2: usuarios buscados por nombre parcial (LIKE). */
    public static Usuario[] usuariosPorNombre(String nombre) throws Exception {
        ConexionBaseDatos baseDatos = null;
        String sql = "SELECT * FROM usuario WHERE nombre LIKE ?";
        try {
            baseDatos = new ConexionBaseDatos();
            PreparedStatement s = baseDatos.crearSentencia(sql);
            s.setString(1, "%" + nombre + "%");
            ResultSet r = baseDatos.consultar(s);
            r.last();
            Usuario[] listado = new Usuario[r.getRow()];
            r.beforeFirst();
            int contador = 0;
            while (r.next()) {
                Usuario alguien = new Usuario();
                alguien.setId(r.getInt("id_usuario"));
                alguien.setClave(r.getString("clave"));
                alguien.setNombre(r.getString("nombre"));
                alguien.setRol(r.getString("rol"));
                alguien.setEmail(r.getString("email"));
                listado[contador] = alguien;
                contador++;
            }
            return listado;
        } finally {
            if (baseDatos != null) baseDatos.desconectar();
        }
    }

    /**
     * Restablece la contraseña de un usuario a partir de su correo registrado,
     * actualiza la base de datos y envía la nueva clave por correo electrónico.
     */
    public static String recuperarClavePorEmail(String email) throws Exception {
        if (email == null || email.trim().isEmpty()) {
            throw new Exception("El correo electrónico es obligatorio");
        }
        ConexionBaseDatos baseDatos = null;
        try {
            baseDatos = new ConexionBaseDatos();
            // 1. Verificar si el usuario existe y obtener su nombre
            String sqlSelect = "SELECT id_usuario, nombre FROM usuario WHERE email = ?";
            PreparedStatement stmtSelect = baseDatos.crearSentencia(sqlSelect);
            stmtSelect.setString(1, email);
            ResultSet r = baseDatos.consultar(stmtSelect);

            if (!r.next()) {
                throw new Exception("El correo ingresado no se encuentra registrado en el sistema.");
            }

            String nombre = r.getString("nombre");

            // 2. Generar clave temporal de 6 dígitos
            String claveTemporal = "temp" + (1000 + (int)(Math.random() * 9000));

            // 3. Actualizar la clave en la base de datos
            String sqlUpdate = "UPDATE usuario SET clave = ? WHERE email = ?";
            PreparedStatement stmtUpdate = baseDatos.crearSentencia(sqlUpdate);
            stmtUpdate.setString(1, claveTemporal);
            stmtUpdate.setString(2, email);
            baseDatos.actualizar(stmtUpdate);

            // 4. Notificar vía ServicioEmail
            ServicioEmail.enviarClaveTemporal(email, nombre, claveTemporal);

            return "Se ha generado tu nueva clave temporal: <b>" + claveTemporal + "</b> (Enviada a: " + email + ").";
        } finally {
            if (baseDatos != null) {
                baseDatos.desconectar();
            }
        }
    }
}
