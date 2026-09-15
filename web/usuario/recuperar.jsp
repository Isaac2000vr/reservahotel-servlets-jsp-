<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String mensaje = request.getParameter("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recuperar Contraseña</title>
    </head>
    <body>
        <center>
            <h1>Recuperación de Contraseña</h1>
            <p>Ingresa tu correo electrónico registrado para restablecer tu clave.</p>
            <hr/>
            <form action="<%=request.getContextPath()%>/usuario" method="post">
                <input type="hidden" name="accion" value="recuperar"/>
                <table>
                    <tr>
                        <th style="text-align: right">Email registrado:</th>
                        <td><input type="email" name="email" required placeholder="ejemplo@reservahotel.com"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <br/>
                            <input type="submit" value="Enviar Clave"/>
                            <input type="reset" value="Limpiar"/>
                        </td>
                    </tr>
                </table>
            </form>
            <hr/>
            <% if (mensaje != null && !mensaje.isEmpty()) { %>
                <p style="color: #00008B; background-color: #F0F8FF; padding: 10px; border: 1px solid #B0C4DE; display: inline-block;">
                    <%= mensaje %>
                </p>
                <br/>
            <% } %>
            <a href="<%=request.getContextPath()%>/usuario/login.jsp">&lt;&lt; Volver a Iniciar Sesión</a>
        </center>
    </body>
</html>
