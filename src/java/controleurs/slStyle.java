/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Epulapp
 */
public class slStyle extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String erreur;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String demande;
        String referer = request.getHeader("Referer");
        String[] tab = referer.split("/");
        String vueReponse = tab[tab.length-1];  //"/home.jsp";
        if(vueReponse.equalsIgnoreCase("OeuvresJDBC")) vueReponse = "index.oe";
        erreur = "";
        HttpSession session = request.getSession(true);
        try {
            //session.setAttribute("css", "lib/bootstrap/css/bootstrap-dark.css");
            demande = getDemande(request);
            if (demande.equalsIgnoreCase("ligth.style")) {
                session.setAttribute("css", "lib/bootstrap/css/bootstrap-Light.css");
            } else if (demande.equalsIgnoreCase("dark.style")) {
                session.setAttribute("css", "lib/bootstrap/css/bootstrap-dark.css");
            } 
        } catch (Exception e) {
            erreur = e.getMessage();
        } finally {
            
            request.getAttribute("pageR");
            request.setAttribute("erreurR", erreur);
            request.setAttribute("pageR", vueReponse); 
            response.sendRedirect(vueReponse);
        }
    }
    
    private String getDemande(HttpServletRequest request) {
        String demande = "";
        demande = request.getRequestURI();
        demande = demande.substring(demande.lastIndexOf("/") + 1);
        return demande;
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
