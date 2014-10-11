<%-- 
    Document   : upload
    Created on 
: 6/10/2014, 03:53:21 PM
    Author     : julian.montoyap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Minipanini</title>
    </head>
    <body>
        <!--<h1>Hello World!</h1>-->
    <center>
        <h1>Ingrese informacion del jugador:</h1>
        <form method="post" action="SubirServlet" enctype="multipart/form-data">
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
                    <td>Edad: </td>
                    <td><input type="text" name="edad" size="50"/></td>
                </tr>
                <tr>
                    <td>num. Camiseta: </td>
                    <td><input type="text" name="num_camiseta" size="50"/></td>
                </tr>  
                <tr>
                    <td>Posicion: </td>
                    <td><input type="text" name="posicion" size="50"/></td> 
                </tr>                
                <tr>
                    <td>Altura: </td>
                    <td><input type="text" name="altura" size="50"/></td>
                </tr>                
                <tr>
                    <td>peso: </td>
                    <td><input type="text" name="peso" size="50"/></td>
                </tr>                
                <tr>
                    <td>Seleccionar foto: </td>
                    <td><input type="file" name="foto" size="50"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table>
        </form>
        <a href="/Minipanini/buscar.jsp">Buscar</a> 
    </center>
</body>
</html>
