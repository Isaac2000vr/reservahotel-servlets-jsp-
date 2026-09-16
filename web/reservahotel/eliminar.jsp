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
        <title>Eliminar Reserva</title>
    </head>
    <body>
        <center>
            <h1>Eliminar Reserva de Hotel</h1>
            <hr/>
            <form action="../reservahotel?accion=buscar&redir=borrar" method="post">
                <table>
                    <tr>
                        <th style="text-align:right">ID a buscar:</th>
                        <td><input type="text" name="id"/></td>
                        <td><input type="submit" value="Buscar"/></td>
                    </tr>
                    <tr><th style="text-align:right">Hotel:</th><td style="text-align:left"><%= (alguna != null) ? alguna.getHotel() : ""%></td></tr>
                    <tr><th style="text-align:right">Hu&eacute;sped:</th><td style="text-align:left"><%= (alguna != null) ? alguna.getHuesped() : ""%></td></tr>
                    <tr><th style="text-align:right">Habitaci&oacute;n:</th><td style="text-align:left"><%= (alguna != null) ? alguna.getHabitacion() : ""%></td></tr>
                    <tr><th style="text-align:right">Ciudad:</th><td style="text-align:left"><%= (alguna != null) ? alguna.getCiudad() : ""%></td></tr>
                    <tr><th style="text-align:right">Valor:</th><td style="text-align:left"><%= (alguna != null) ? alguna.getValor() : ""%></td></tr>
                </table>
            </form>
            <hr/>
            <% if (alguna != null) { %>
            <form action="../reservahotel?accion=borrar" method="post">
                <input type="hidden" name="id" value="<%= alguna.getId()%>"/>
                <input type="submit" value="Eliminar"/>
            </form>
            <% } %>
            <hr/>
            <p style="color:#FF0000;"><%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : ""%></p>
            <% request.getSession().setAttribute("reserva.buscar", null); %>
            <a href="../index.jsp">&lt;&lt;: VOLVER AL MENU</a>
        </center>
    </body>
</html>