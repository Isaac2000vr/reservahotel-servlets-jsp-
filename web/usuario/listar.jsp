<%@page import="reservahotel.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%
    if (session.getAttribute("usuario.login") == null) {
        getServletContext().getRequestDispatcher("/usuario/login.jsp").forward(request, response);
    }
    Usuario[] listado = (Usuario[]) session.getAttribute("usuario.listar");
    if (listado == null) {
        listado = (Usuario[]) session.getAttribute("usuario.reporte");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Todos los Usuarios</title>
    </head>
    <body>
        <center>
            <h1>Todos los Usuarios Agregados al Sistema</h1>

            <h3>Reporte 1: Usuarios por rol</h3>
            <form action="../usuario" method="get">
                <input type="hidden" name="accion" value="reportePorRol"/>
                Rol: 
                <select name="rol">
                    <option value="admin">admin</option>
                    <option value="empleado">empleado</option>
                </select>
                <input type="submit" value="Generar Reporte"/>
            </form>

            <h3>Reporte 2: Usuarios por nombre</h3>
            <form action="../usuario" method="get">
                <input type="hidden" name="accion" value="reportePorNombre"/>
                Nombre: <input type="text" name="nombre"/>
                <input type="submit" value="Generar Reporte"/>
            </form>
            <hr/>

            <% if (listado == null || listado.length <= 0) { %>
            <p>Resultado: 0 Usuarios encontrados en el Sistema</p>
            <% } else { %>
            <table border="1">
                <thead>
                    <tr>
                        <th>Item</th><th>ID</th><th>Nombre</th><th>Email</th><th>Rol</th>
                    </tr>
                </thead>
                <tbody>
                    <% int contador = 0;
                        for (Usuario alguien : listado) {
                            contador = contador + 1;
                    %>
                    <tr>
                        <td><%= contador%></td>
                        <td><%= alguien.getId()%></td>
                        <td><%= alguien.getNombre()%></td>
                        <td><%= alguien.getEmail()%></td>
                        <td><%= alguien.getRol()%></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } %>
            <hr/>
            <% session.setAttribute("usuario.listar", null);
               session.setAttribute("usuario.reporte", null); %>
            <a href="../index.jsp">&lt;&lt;: VOLVER AL MENU</a>
        </center>
    </body>
</html>