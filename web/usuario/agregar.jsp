<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("usuario.login") == null) {
        getServletContext().getRequestDispatcher("/usuario/login.jsp").forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Usuario al Sistema</title>
    </head>
    <body>
        <center>
            <h1>Agregar Usuario</h1>
            <hr/>
            <form action="../usuario" method="post">
                <input type="hidden" name="accion" value="agregar"/>
                <table>
                    <tr>
                        <th style="text-align: right">Nombre:</th>
                        <td><input type="text" name="nombre"/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Clave:</th>
                        <td><input type="password" name="clave"/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Email:</th>
                        <td><input type="text" name="email"/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Rol:</th>
                        <td>
                            <select name="rol">
                                <option value="admin">Administrador</option>
                                <option value="empleado">Empleado</option>
                                <option value="huesped">Huésped</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><input type="submit" value="ENTRAR"/></th>
                        <td><input type="reset" value="Restablecer"/></td>
                    </tr>
                </table>
            </form>
            <hr/>
            <p style="color:#FF0000;">
                <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : ""%>
            </p>
        </center>
    </body>
</html>