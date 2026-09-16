<%@page import="reservahotel.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("usuario.login") == null) {
        getServletContext().getRequestDispatcher("/usuario/login.jsp").forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
    Usuario alguien = (Usuario) request.getSession().getAttribute("usuario.buscar");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Usuario</title>
    </head>
    <body>
        <center>
            <h1>Eliminar Usuario</h1>
            <hr/>
            <form action="../usuario?accion=buscar&redir=borrar" method="post">
                <table>
                    <tr>
                        <th style="text-align: right">ID a buscar:</th>
                        <td><input type="text" name="id"/></td>
                        <td><input type="submit" value="Buscar"/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Nombre:</th>
                        <td style="text-align: left"><%= (alguien != null) ? alguien.getNombre() : ""%></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Email:</th>
                        <td style="text-align: left"><%= (alguien != null) ? alguien.getEmail() : ""%></td>
                    </tr>
                </table>
            </form>
            <hr/>
            <% if (alguien != null) { %>
            <form action="../usuario?accion=borrar" method="post">
                <input type="hidden" name="id" value="<%= alguien.getId()%>"/>
                <input type="submit" value="Eliminar"/>
            </form>
            <% } %>
            <hr/>
            <p style="color:#FF0000;">
                <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : ""%>
            </p>
            <% request.getSession().setAttribute("usuario.buscar", null); %>
        </center>
    </body>
</html>