<%@page import="reservahotel.modelo.ReservaHotel"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%
    if (session.getAttribute("usuario.login") == null) {
        getServletContext().getRequestDispatcher("/usuario/login.jsp").forward(request, response);
    }
    ReservaHotel[] listado = (ReservaHotel[]) session.getAttribute("reserva.listar");
    if (listado == null) {
        listado = (ReservaHotel[]) session.getAttribute("reserva.reporte");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservas de Hotel</title>
    </head>
    <body>
        <center>
            <h1>Reservas de Hotel</h1>

            <h3>Reporte 1: Reservas por ciudad</h3>
            <form action="../reservahotel" method="get">
                <input type="hidden" name="accion" value="reportePorCiudad"/>
                Ciudad: <input type="text" name="ciudad"/>
                <input type="submit" value="Generar Reporte"/>
            </form>

            <h3>Reporte 2: Reservas por empleado y rango de fechas</h3>
            <form action="../reservahotel" method="get">
                <input type="hidden" name="accion" value="reportePorEmpleado"/>
                ID Empleado: <input type="number" name="empleadoId"/>
                Desde: <input type="date" name="desde"/>
                Hasta: <input type="date" name="hasta"/>
                <input type="submit" value="Generar Reporte"/>
            </form>
            <hr/>

            <% if (listado == null || listado.length <= 0) { %>
            <p>Resultado: 0 reservas encontradas</p>
            <% } else { %>
            <table border="1">
                <thead>
                    <tr>
                        <th>Item</th><th>ID</th><th>Hotel</th><th>Hu&eacute;sped</th>
                        <th>Ciudad</th><th>Fecha Inicio</th><th>Fecha Fin</th><th>Valor</th>
                    </tr>
                </thead>
                <tbody>
                    <% int contador = 0;
                        for (ReservaHotel r : listado) {
                            contador = contador + 1;
                    %>
                    <tr>
                        <td><%= contador%></td>
                        <td><%= r.getId()%></td>
                        <td><%= r.getHotel()%></td>
                        <td><%= r.getHuesped()%></td>
                        <td><%= r.getCiudad()%></td>
                        <td><%= r.getFechaInicio()%></td>
                        <td><%= r.getFechaFin()%></td>
                        <td><%= r.getValor()%></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } %>
            <hr/>
            <% session.setAttribute("reserva.listar", null);
               session.setAttribute("reserva.reporte", null); %>
            <a href="../index.jsp">&lt;&lt;: VOLVER AL MENU</a>
        </center>
    </body>
</html>
