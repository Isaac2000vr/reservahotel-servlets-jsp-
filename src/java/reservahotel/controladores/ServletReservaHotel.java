package reservahotel.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reservahotel.modelo.CRUDReservaHotel;
import reservahotel.modelo.ReservaHotel;

@WebServlet(name = "ServletReservaHotel", urlPatterns = {"/reservahotel"})
public class ServletReservaHotel extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String accion = request.getParameter("accion");

            if (accion.equals("agregar")) {
                CRUDReservaHotel crud = new CRUDReservaHotel();
                llenarDesdeRequest(crud.getAlguna(), request);
                crud.agregarReserva();
                response.sendRedirect("reservahotel/agregar.jsp?mensaje=Reserva Agregada al Sistema");

            } else if (accion.equals("buscar")) {
                ReservaHotel alguna = CRUDReservaHotel.consultarReserva(
                        Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("reserva.buscar", alguna);
                String redireccion = request.getParameter("redir");
                if (redireccion.equals("borrar")) {
                    response.sendRedirect("reservahotel/eliminar.jsp");
                } else if (redireccion.equals("modificar")) {
                    response.sendRedirect("reservahotel/modificar.jsp");
                } else {
                    response.sendRedirect("reservahotel/buscar.jsp");
                }

            } else if (accion.equals("modificar")) {
                CRUDReservaHotel crud = new CRUDReservaHotel();
                crud.getAlguna().setId(Integer.parseInt(request.getParameter("id")));
                llenarDesdeRequest(crud.getAlguna(), request);
                crud.modificarReserva();
                response.sendRedirect("reservahotel/modificar.jsp?mensaje=Reserva Modificada en el Sistema");

            } else if (accion.equals("borrar")) {
                CRUDReservaHotel crud = new CRUDReservaHotel();
                crud.getAlguna().setId(Integer.parseInt(request.getParameter("id")));
                crud.eliminarReserva();
                response.sendRedirect("reservahotel/eliminar.jsp?mensaje=Reserva Eliminada del Sistema");

            } else if (accion.equals("listartodo")) {
                ReservaHotel[] listado = CRUDReservaHotel.listarTodasLasReservas();
                request.getSession().setAttribute("reserva.listar", listado);
                response.sendRedirect("reservahotel/listar.jsp");

            } else if (accion.equals("reportePorCiudad")) {
                ReservaHotel[] listado = CRUDReservaHotel.reservasPorCiudad(request.getParameter("ciudad"));
                request.getSession().setAttribute("reserva.reporte", listado);
                response.sendRedirect("reservahotel/listar.jsp");

            } else if (accion.equals("reportePorEmpleado")) {
                int empleadoId = Integer.parseInt(request.getParameter("empleadoId"));
                Date desde = Date.valueOf(request.getParameter("desde"));
                Date hasta = Date.valueOf(request.getParameter("hasta"));
                ReservaHotel[] listado = CRUDReservaHotel.reservasPorEmpleadoYRango(empleadoId, desde, hasta);
                request.getSession().setAttribute("reserva.reporte", listado);
                response.sendRedirect("reservahotel/listar.jsp");

            } else {
                response.sendRedirect("mensaje.jsp?mensaje=La Accion Solicitada no es Correcta");
            }
        } catch (Exception error) {
            response.sendRedirect("mensaje.jsp?mensaje=" + error.getMessage());
        } finally {
            out.close();
        }
    }

    private void llenarDesdeRequest(ReservaHotel r, HttpServletRequest request) {
        r.setFecha(Date.valueOf(request.getParameter("fecha")));
        r.setHotel(request.getParameter("hotel"));
        r.setHuesped(request.getParameter("huesped"));
        r.setFechaInicio(Date.valueOf(request.getParameter("fechaInicio")));
        r.setFechaFin(Date.valueOf(request.getParameter("fechaFin")));
        r.setValor(new BigDecimal(request.getParameter("valor")));
        r.setHabitacion(request.getParameter("habitacion"));
        r.setNumAcompanantes(Integer.parseInt(request.getParameter("numAcompanantes")));
        r.setPais(request.getParameter("pais"));
        r.setDepartamento(request.getParameter("departamento"));
        r.setCiudad(request.getParameter("ciudad"));
        r.setHoraCheckin(Time.valueOf(request.getParameter("horaCheckin") + ":00"));
        r.setHoraCheckout(Time.valueOf(request.getParameter("horaCheckout") + ":00"));
        r.setEmpleadoAtiendeId(Integer.parseInt(request.getParameter("empleadoAtiendeId")));
        String despideStr = request.getParameter("empleadoDespideId");
        r.setEmpleadoDespideId((despideStr != null && !despideStr.isEmpty())
                ? Integer.parseInt(despideStr) : null);
        r.setDescripcion(request.getParameter("descripcion"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}