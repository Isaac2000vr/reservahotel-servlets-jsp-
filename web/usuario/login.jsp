<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String mensaje = request.getParameter("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar Sesión</title>
    </head>
    <body>
        <center>
            <h1>Iniciar Sesión en el Sistema</h1>
            <hr/>
            <form action="<%=request.getContextPath()%>/usuario" method="post">
                <input type="hidden" name="accion" value="login"/>
                <table>
                    <tr>
                        <th style="text-align: right">Email:</th>
                        <td><input type="text" name="email"/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Clave:</th>
                        <td><input type="password" name="clave"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="ENTRAR"/></td>
                        <td><input type="reset" value="Restablecer"/></td>
                    </tr>
                </table>
            </form>
            <br/>
            <a href="<%=request.getContextPath()%>/usuario/recuperar.jsp">&iquest;Olvidaste tu contrase&ntilde;a? Recuperar clave aqu&iacute;</a>
            <hr/>
            <% if (mensaje != null && !mensaje.isEmpty()) { %>
                <p style="color: #FF0000;"><%= mensaje %></p>
            <% } %>
        </center>
    </body>
</html>
