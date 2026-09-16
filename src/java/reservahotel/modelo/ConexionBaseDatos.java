package reservahotel.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConexionBaseDatos {
    // atributos
    protected String driver = "com.mysql.cj.jdbc.Driver";
    protected String nombreIPServidorBD = "localhost";
    protected String url = "jdbc:mysql://";
    protected int puertoServidorBD = 3306;
    protected String usuarioBD = "root";
    protected String passwordUsuarioBD = "";
    protected String nombreBD = "reservahotel_db";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet filasConsulta;

    // constructores
    public ConexionBaseDatos() throws Exception {
        url = url + nombreIPServidorBD + ":" + puertoServidorBD + "/" + nombreBD
                + "?useSSL=false&serverTimezone=UTC";
        this.conectar();
    }

    public ConexionBaseDatos(String driver, String servidor, String url, int puerto,
                              String usuarioBD, String passwordUsuarioBD, String nombreBD) throws Exception {
        this.driver = driver;
        this.nombreIPServidorBD = servidor;
        this.url = url;
        this.puertoServidorBD = puerto;
        this.usuarioBD = usuarioBD;
        this.passwordUsuarioBD = passwordUsuarioBD;
        this.nombreBD = nombreBD;
        this.conectar();
    }

    // operaciones sobre BD
    public void conectar() throws Exception {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            throw new Exception("Error de Driver " + ex.getMessage());
        }
        try {
            conexion = DriverManager.getConnection(url, usuarioBD, passwordUsuarioBD);
        } catch (SQLException ex) {
            throw new Exception("Error de Conexion \nCodigo:"
                    + ex.getErrorCode() + " Explicacion:" + ex.getMessage());
        }
    }

    public int actualizar(PreparedStatement sentencia) throws Exception {
        try {
            return sentencia.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException("Error al ejecutar sentencia BD Conexion \nCodigo:"
                    + ex.getErrorCode() + " Explicacion:" + ex.getMessage());
        }
    }

    public ResultSet consultar(PreparedStatement sentencia) throws Exception {
        try {
            return sentencia.executeQuery();
        } catch (SQLException ex) {
            throw new SQLException("Error al ejecutar sentencia BD Conexion "
                    + ex.getMessage());
        }
    }

    public void desconectar() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            conexion = null;
        }
    }

    public PreparedStatement crearSentencia(String sql) throws Exception {
    try {
        return conexion.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            throw new SQLException("Error de Sentencia DB \nCodigo:"
                    + ex.getErrorCode() + " Explicacion:" + ex.getMessage());
        }
    }
}