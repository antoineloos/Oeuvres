/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import dal.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modeles.Proprietaire;

/**
 *
 * @author Epulapp
 */
public class slUser extends HttpServlet {
    private String erreur;
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
        String demande;
        String vueReponse = "/index.jsp";
        erreur = "";
        try {
            demande = getDemande(request);
            if (demande.equalsIgnoreCase("ajouter.user")) {
                vueReponse = creerUser(request);
            } 
            
            else if(demande.equalsIgnoreCase("inscription.user"))
            {
                vueReponse = inscriptionUser(request);
            }
        } catch (Exception e) {
            erreur = e.getMessage();
        } finally {
            request.setAttribute("erreurR", erreur);
            request.setAttribute("pageR", vueReponse);
            RequestDispatcher dsp = request.getRequestDispatcher("/index.jsp");
            if (vueReponse.contains(".user")) {
                dsp = request.getRequestDispatcher(vueReponse);
            }
            dsp.forward(request, response);
        }
    }
    
    private String creerUser(HttpServletRequest request) throws Exception {
        String vueReponse;
        try {
            
            vueReponse = "/createUser.jsp";
            return (vueReponse);
        } catch (Exception e) {
            throw e;
        }
    }
    
    private String inscriptionUser(HttpServletRequest request) throws  Exception
    {
        String vueReponse;
        UserDao userDao = new UserDao();
        Proprietaire user = new Proprietaire();
        try {

            if(request.getParameter("txtNom")!="")
            {
                user.setNom_proprietaire(request.getParameter("txtNom").toString());
            }
            
            if(request.getParameter("txtPrenom")!="")
            {
                user.setPrenom_proprietaire(request.getParameter("txtPrenom").toString());
            }
            
            if(request.getParameter("txtLogin")!="")
            {
                user.setLogin(request.getParameter("txtLogin").toString());
            }
            
            if(request.getParameter("txtPwd")!="" && request.getParameter("txtConfPwd")!="")
            {
                if(request.getParameter("txtPwd").equalsIgnoreCase(request.getParameter("txtConfPwd")))
                {
                    user.setPwd(request.getParameter("txtPwd").toString());
                }
            }
            
            userDao.ajouter(user);
            
            vueReponse = "/login.jsp";
            return (vueReponse);
        } catch (Exception e) {
            throw e;
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
