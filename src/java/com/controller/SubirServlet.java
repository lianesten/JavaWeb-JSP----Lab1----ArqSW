/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author julian.montoyap
 */
@MultipartConfig(maxFileSize = 16177215)//defino el tamaño maximo de los datos a enviar
public class SubirServlet extends HttpServlet {
    private String dbURL = "jdbc:mysql://localhost:3306/julianestenDB";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String edad = request.getParameter("edad");
        String num_camiseta = request.getParameter("num_camiseta");
        String posicion = request.getParameter("posicion");
        String altura = request.getParameter("altura");
        String peso = request.getParameter("peso");
        InputStream inputStream = null;//defino el canal de comunicacion
        Part filePart = request.getPart("foto");
        if(filePart!=null){//verifico que me traiga algo
            //MIRO SI SE ESTAN TRALLENDO ADECUADAMENTE LOS ARCHIVOS
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());//fORMATO MIME DEL ARCHIVO
            inputStream = filePart.getInputStream();//asigno el objeto al inputStream
        }
        Connection conn = null;//
        String message = null;
        
        try {
            //lo siguiente cambia segun el motor
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //url, usuario, contrasenia para la conexion a la bd
            conn = (Connection)DriverManager.getConnection(dbURL,"root","root");
            //preparamos el estamento
            String sql = "INSERT INTO jugadores(nombre,apellido,edad,num_camiseta,posicion,altura,peso,photo) "
                    + "VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            //fetch
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, edad);
            statement.setString(4,num_camiseta );
            statement.setString(5, posicion);
            statement.setString(6, altura);
            statement.setString(7, peso);
            //para insertar una foto debo hacer el sgte control
            if(inputStream!=null){
                statement.setBlob(8, inputStream);//8 significa la posicion del parametro donde se enceutra la foto
            }
            //enviar los estamentos a la BD
            int row = statement.executeUpdate();
            if(row>0){
                message = "Archivo actualizado exitosamente!!";
            }
        } catch (SQLException ex) {
            message = "Error: "+ex.getMessage();
            ex.printStackTrace();
        }
        finally{
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    
                }
            }   
            //Colocar el mensaje en el ambito del request
            request.setAttribute("Message", message);
            //reenviar a la pagina del mensaje
            getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
        }
        
        
        
        /*try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. *
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SubirServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubirServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
      
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
