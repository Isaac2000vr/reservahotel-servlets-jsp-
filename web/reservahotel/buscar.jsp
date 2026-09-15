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
        <title>Buscar Reserva</title>
    </head>
    <body>
        <center>
            <h1>Buscar Reserva de Hotel</h1>
            <hr/>
            <form action="../reservahotel?accion=buscar&redir=buscar" method="post">
                <table>
                    <tr>
                        <th style="text-align:right">ID:</th>
                        <td><input type="text" name="id"/></td>
                        <td><input type="submit" value="Buscar"/></td>
                    </tr>
                    <tr><th style="text-align:right">Hotel:</th><td style="text-align:left"><%= (alguna != null) ? alguna.getHotel() : ""%></td></tr>
                    <tr><th style="text-align:right">Huésped:</th><td style="text-align:left"><%= (alguna != null) ? alguna.getHuesped() : ""%></td></tr>
                    <tr><th style="text-align:right">Habitación:</th><td style="text-align:left"><%= (alguna != null) ? alguna.getHabitacion() : ""%></td></tr>
                    <tr><th style="text-align:right">Ciudad:</th><td style="text-align:left"><%= (alguna != null) ? alguna.getCiudad() : ""%></td></tr>
                    <tr><th style="text-align:right">Valor:</th><td style="text-align:left"><%= (alguna != null) ? alguna.getValor() : ""%></td></tr>
                </table>
            </form>
            <hr/>
            <p style="color:#FF0000;"><%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : ""%></p>
            <% request.getSession().setAttribute("reserva.buscar", null); %>
        </center>
    </body>
</html>
