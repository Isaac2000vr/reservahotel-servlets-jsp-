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
        <title>Modificar Usuario</title>
    </head>
    <body>
        <center>
            <h1>Modificar Usuario</h1>
            <hr/>
            <form action="../usuario?accion=buscar&redir=modificar" method="post">
                <table>
                    <tr>
                        <th style="text-align: right">ID a buscar:</th>
                        <td><input type="text" name="id"/></td>
                        <td><input type="submit" value="Buscar"/></td>
                    </tr>
                </table>
            </form>
            <hr/>
            <% if (alguien != null) { %>
            <form action="../usuario" method="post">
                <input type="hidden" name="accion" value="modificar"/>
                <input type="hidden" name="id" value="<%= alguien.getId()%>"/>
                <table>
                    <tr>
                        <th style="text-align: right">Nombre:</th>
                        <td><input type="text" name="nombre" value="<%= alguien.getNombre()%>"/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Clave:</th>
                        <td><input type="password" name="clave" value="<%= alguien.getClave()%>"/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Email:</th>
                        <td><input type="text" name="email" value="<%= alguien.getEmail()%>"/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Rol:</th>
                        <td>
                            <select name="rol">
                                <option value="admin" <%= alguien.getRol().equals("admin") ? "selected" : ""%>>Administrador</option>
                                <option value="empleado" <%= alguien.getRol().equals("empleado") ? "selected" : ""%>>Empleado</option>
                                <option value="huesped" <%= alguien.getRol().equals("huesped") ? "selected" : ""%>>Huésped</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><input type="submit" value="Modificar"/></th>
                    </tr>
                </table>
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
