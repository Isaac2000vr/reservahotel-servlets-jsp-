<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensaje del Sistema</title>
    </head>
    <body>
        <center>
            <h1>
                <%= request.getParameter("mensaje")%>
            </h1>
            <hr/>
            <a href="index.jsp">&lt;&lt;&lt; Volver :::</a>
        </center>
    </body>
</html>
