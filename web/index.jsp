<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("usuario.login") == null) {
        getServletContext().getRequestDispatcher("/usuario/login.jsp").forward(request, response);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menú de la Aplicación</title>
    </head>
    <body>
        <center>
            <h2>Menú de la aplicación</h2>
            <hr/>
            <table border="0">
                <tr><td><a href="usuario/agregar.jsp">Agregar Usuario</a></td></tr>
                <tr><td><a href="usuario/buscar.jsp">Buscar Usuario</a></td></tr>
                <tr><td><a href="usuario/modificar.jsp">Modificar Usuario</a></td></tr>
                <tr><td><a href="usuario/eliminar.jsp">Eliminar Usuario</a></td></tr>
                <tr><td><a href="usuario?accion=listartodo">Listar Usuarios</a></td></tr>
                <tr><td><a href="reservahotel/agregar.jsp">Agregar Reserva</a></td></tr>
                <tr><td><a href="reservahotel/buscar.jsp">Buscar Reserva</a></td></tr>
                <tr><td><a href="reservahotel/modificar.jsp">Modificar Reserva</a></td></tr>
                <tr><td><a href="reservahotel/eliminar.jsp">Eliminar Reserva</a></td></tr>
                <tr><td><a href="reservahotel?accion=listartodo">Listar Reservas</a></td></tr>
                <tr><td><a href="usuario?accion=salir">Salir</a></td></tr>
            </table>
            <hr/>
        </center>
    </body>
</html>
