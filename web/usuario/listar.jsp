<%@page import="reservahotel.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%
    if (session.getAttribute("usuario.login") == null) {
        getServletContext().getRequestDispatcher("/usuario/login.jsp").forward(request, response);
    }
    Usuario[] listado = (Usuario[]) session.getAttribute("usuario.listar");
    String mensaje = null;
    if (listado == null || listado.length <= 0) {
        mensaje = "Resultado: 0 Usuarios encontrados en el Sistema";
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
            <% if (mensaje != null) {
                out.print(mensaje);
            } else { %>
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
            <a href="../index.jsp">&lt;&lt;: VOLVER AL MENU</a>
        </center>
    </body>
</html>