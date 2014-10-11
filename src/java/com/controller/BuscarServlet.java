/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author julianesten
 */
public class BuscarServlet extends HttpServlet {
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
        
        Connection conn = null;//
        
                try {
            //lo siguiente cambia segun el motor
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //url, usuario, contrasenia para la conexion a la bd
            conn = (Connection)DriverManager.getConnection(dbURL,"root","root");
            //preparamos el estamento
            String sql = "SELECT * FROM jugadores WHERE nombre = ? AND apellido = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            //fetch
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            //enviar los estamentos a la BD
           /* ResultSet resultado = statement.executeQuery();
            List res = new List
            while(resultado.next()){
                
                        resultado.getString("");
            
            }*/
                    EntityManager result = Persistence.createEntityManagerFactory("MinipaniniPU").createEntityManager();
                    List datos = result.createNamedQuery(sql).getResultList();
                    request.setAttribute("datos", datos);
                    RequestDispatcher rd = request.getRequestDispatcher("Resultado.jsp");
                    rd.forward(request, response);
                    
           // int row = statement.executeUpdate();
            //if(row>0){
                //List datos = 
                //message = "Archivo actualizado exitosamente!!";
            //}
        } catch (SQLException ex) {
            //message = "Error: "+ex.getMessage();
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
            //request.setAttribute("Message", message);
            //reenviar a la pagina del mensaje
            getServletContext().getRequestDispatcher("/Resultado.jsp");
        }
        
        
      /*  try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. *
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BuscarServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuscarServlet at " + request.getContextPath() + "</h1>");
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
