<%@page import="reservahotel.modelo.ReservaHotel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("usuario.login") == null) {
        getServletContext().getRequestDispatcher("/usuario/login.jsp").forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
    ReservaHotel alguna = (ReservaHotel) request.getSession().getAttribute("reserva.buscar");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Reserva</title>
    </head>
    <body>
        <center>
            <h1>Modificar Reserva de Hotel</h1>
            <hr/>
            <form action="../reservahotel?accion=buscar&redir=modificar" method="post">
                <table>
                    <tr><th style="text-align:right">ID a buscar:</th><td><input type="text" name="id"/></td><td><input type="submit" value="Buscar"/></td></tr>
                </table>
            </form>
            <hr/>
            <% if (alguna != null) { %>
            <form action="../reservahotel" method="post">
                <input type="hidden" name="accion" value="modificar"/>
                <input type="hidden" name="id" value="<%= alguna.getId()%>"/>
                <table>
                    <tr><th style="text-align:right">Fecha registro:</th><td><input type="date" name="fecha" value="<%= alguna.getFecha()%>"/></td></tr>
                    <tr><th style="text-align:right">Hotel:</th><td><input type="text" name="hotel" value="<%= alguna.getHotel()%>"/></td></tr>
                    <tr><th style="text-align:right">Huésped:</th><td><input type="text" name="huesped" value="<%= alguna.getHuesped()%>"/></td></tr>
                    <tr><th style="text-align:right">Fecha inicio:</th><td><input type="date" name="fechaInicio" value="<%= alguna.getFechaInicio()%>"/></td></tr>
                    <tr><th style="text-align:right">Fecha fin:</th><td><input type="date" name="fechaFin" value="<%= alguna.getFechaFin()%>"/></td></tr>
                    <tr><th style="text-align:right">Valor:</th><td><input type="number" step="0.01" name="valor" value="<%= alguna.getValor()%>"/></td></tr>
                    <tr><th style="text-align:right">Habitación:</th><td><input type="text" name="habitacion" value="<%= alguna.getHabitacion()%>"/></td></tr>
                    <tr><th style="text-align:right">N° Acompañantes:</th><td><input type="number" name="numAcompanantes" value="<%= alguna.getNumAcompanantes()%>"/></td></tr>
                    <tr><th style="text-align:right">País:</th><td><input type="text" name="pais" value="<%= alguna.getPais()%>"/></td></tr>
                    <tr><th style="text-align:right">Departamento:</th><td><input type="text" name="departamento" value="<%= alguna.getDepartamento()%>"/></td></tr>
                    <tr><th style="text-align:right">Ciudad:</th><td><input type="text" name="ciudad" value="<%= alguna.getCiudad()%>"/></td></tr>
                    <tr><th style="text-align:right">Hora check-in:</th><td><input type="time" name="horaCheckin" value="<%= alguna.getHoraCheckin()%>"/></td></tr>
                    <tr><th style="text-align:right">Hora check-out:</th><td><input type="time" name="horaCheckout" value="<%= alguna.getHoraCheckout()%>"/></td></tr>
                    <tr><th style="text-align:right">ID empleado atiende:</th><td><input type="number" name="empleadoAtiendeId" value="<%= alguna.getEmpleadoAtiendeId()%>"/></td></tr>
                    <tr><th style="text-align:right">ID empleado despide:</th><td><input type="number" name="empleadoDespideId" value="<%= alguna.getEmpleadoDespideId() != null ? alguna.getEmpleadoDespideId() : ""%>"/></td></tr>
                    <tr><th style="text-align:right">Descripción:</th><td><textarea name="descripcion"><%= alguna.getDescripcion()%></textarea></td></tr>
                    <tr><th><input type="submit" value="Modificar"/></th></tr>
                </table>
            </form>
            <% } %>
            <hr/>
            <p style="color:#FF0000;"><%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : ""%></p>
            <% request.getSession().setAttribute("reserva.buscar", null); %>
        </center>
    </body>
</html>