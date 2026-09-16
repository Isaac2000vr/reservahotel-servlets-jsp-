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
        <title>Agregar Reserva de Hotel</title>
    </head>
    <body>
        <center>
            <h1>Agregar Reserva de Hotel</h1>
            <hr/>
            <form action="../reservahotel" method="post">
                <input type="hidden" name="accion" value="agregar"/>
                <table>
                    <tr><th style="text-align:right">Fecha registro:</th><td><input type="date" name="fecha"/></td></tr>
                    <tr><th style="text-align:right">Hotel:</th><td><input type="text" name="hotel"/></td></tr>
                    <tr><th style="text-align:right">Huésped:</th><td><input type="text" name="huesped"/></td></tr>
                    <tr><th style="text-align:right">Fecha inicio:</th><td><input type="date" name="fechaInicio"/></td></tr>
                    <tr><th style="text-align:right">Fecha fin:</th><td><input type="date" name="fechaFin"/></td></tr>
                    <tr><th style="text-align:right">Valor:</th><td><input type="number" step="0.01" name="valor"/></td></tr>
                    <tr><th style="text-align:right">Habitación:</th><td><input type="text" name="habitacion"/></td></tr>
                    <tr><th style="text-align:right">N° Acompañantes:</th><td><input type="number" name="numAcompanantes" value="0"/></td></tr>
                    <tr><th style="text-align:right">País:</th><td><input type="text" name="pais"/></td></tr>
                    <tr><th style="text-align:right">Departamento:</th><td><input type="text" name="departamento"/></td></tr>
                    <tr><th style="text-align:right">Ciudad:</th><td><input type="text" name="ciudad"/></td></tr>
                    <tr><th style="text-align:right">Hora check-in:</th><td><input type="time" name="horaCheckin"/></td></tr>
                    <tr><th style="text-align:right">Hora check-out:</th><td><input type="time" name="horaCheckout"/></td></tr>
                    <tr><th style="text-align:right">ID empleado que atiende:</th><td><input type="number" name="empleadoAtiendeId"/></td></tr>
                    <tr><th style="text-align:right">ID empleado que despide:</th><td><input type="number" name="empleadoDespideId"/></td></tr>
                    <tr><th style="text-align:right">Descripción:</th><td><textarea name="descripcion"></textarea></td></tr>
                    <tr><th><input type="submit" value="ENTRAR"/></th><td><input type="reset" value="Restablecer"/></td></tr>
                </table>
            </form>
            <hr/>
            <p style="color:#FF0000;"><%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : ""%></p>
        </center>
    </body>
</html>
