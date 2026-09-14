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
}
