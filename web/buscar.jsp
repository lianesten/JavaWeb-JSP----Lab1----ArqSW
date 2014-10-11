<%-- 
    Document   : buscar
    Created on : 8/10/2014, 01:48:40 PM
    Author     : julianesten
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar jugador</title>
    </head>
    <body>
        <h1>BUSCAR JUGADOR</h1>
        <form method="post" action="BuscarServlet" enctype="multipart/form-data">
            <table border="0">
                <tr>
                    <td>Nombres: </td>
                    <td><input type="text" name="nombre" size="50"/></td>
                </tr>
                <tr>
                    <td>Apellidos: </td>
                    <td><input type="text" name="apellido" size="50"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Buscar">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
