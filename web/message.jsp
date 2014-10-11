<%-- 
    Document   : message
    Created on : 6/10/2014, 03:50:58 PM
    Author     : julian.montoyap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Enhorabuena!</h1>
         <center>
            <h3><%=request.getAttribute("Message")%></h3>
         </center>
    </body>
</html>
