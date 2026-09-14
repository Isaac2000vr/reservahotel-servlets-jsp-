package reservahotel.modelo;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

/**
 * Clase ReservaHotel, representa la tabla reserva_hotel de la BD (Ejercicio 31).
 */
public class ReservaHotel {

    private int id;
    private Date fecha;
    private String hotel;
    private String huesped;
    private Date fechaInicio;
    private Date fechaFin;
    private BigDecimal valor;
    private String habitacion;
    private int numAcompanantes;
    private String pais;
    private String departamento;
    private String ciudad;
    private Time horaCheckin;
    private Time horaCheckout;
    private int empleadoAtiendeId;
    private Integer empleadoDespideId; // puede ser null
    private String descripcion;

    public ReservaHotel() {
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getHotel() { return hotel; }
    public void setHotel(String hotel) { this.hotel = hotel; }

    public String getHuesped() { return huesped; }
    public void setHuesped(String huesped) { this.huesped = huesped; }

    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }

    public Date getFechaFin() { return fechaFin; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getHabitacion() { return habitacion; }
    public void setHabitacion(String habitacion) { this.habitacion = habitacion; }

    public int getNumAcompanantes() { return numAcompanantes; }
    public void setNumAcompanantes(int numAcompanantes) { this.numAcompanantes = numAcompanantes; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public Time getHoraCheckin() { return horaCheckin; }
    public void setHoraCheckin(Time horaCheckin) { this.horaCheckin = horaCheckin; }

    public Time getHoraCheckout() { return horaCheckout; }
    public void setHoraCheckout(Time horaCheckout) { this.horaCheckout = horaCheckout; }

    public int getEmpleadoAtiendeId() { return empleadoAtiendeId; }
    public void setEmpleadoAtiendeId(int empleadoAtiendeId) { this.empleadoAtiendeId = empleadoAtiendeId; }

    public Integer getEmpleadoDespideId() { return empleadoDespideId; }
    public void setEmpleadoDespideId(Integer empleadoDespideId) { this.empleadoDespideId = empleadoDespideId; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}