/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controleurs;

import dal.AdherentDao;
import dal.OeuvreDao;
import dal.ReservationDao;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import outils.*;
import modeles.*;

/**
 *
 * @author alain
 */

public class slReservation extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    private String erreur;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String demande;
        String vueReponse = "/home.jsp";
        erreur = "";
        try {
            demande = getDemande(request);
            if (demande.equalsIgnoreCase("reserver.res")) {
                vueReponse = reserverOeuvre(request);
            } else if (demande.equalsIgnoreCase("enregistrerReservation.res")) {
                vueReponse = enregistrerReservation(request);
            } else if (demande.equalsIgnoreCase("listeReservations.res")) {
                vueReponse = listeReservations(request);
            } else if (demande.equalsIgnoreCase("confirmerReservation.res")) {
                vueReponse = confirmerReservation(request);
            } else if (demande.equalsIgnoreCase("supprimerReservation.res")) {
                vueReponse = supprimerReservation(request);
            }
        } catch (Exception e) {
            erreur = e.getMessage();
        } finally {
            request.setAttribute("erreurR", erreur);
            request.setAttribute("pageR", vueReponse); 
            RequestDispatcher dsp = request.getRequestDispatcher("/index.jsp");
            if (vueReponse.contains(".res"))
                dsp = request.getRequestDispatcher(vueReponse);
            dsp.forward(request, response);
        }
    }

    /**
     * Transforme dans la base de données une réservation en Attente
     * en une réservation Confirmée
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String confirmerReservation(HttpServletRequest request) throws Exception {

        try {
            
            return ("listeReservations.res");
        } catch (Exception e) {
            throw e;
        }
    }

    private String supprimerReservation(HttpServletRequest request) throws Exception {
        
        try {

            return ("listeReservations.res");
        } catch (Exception e) {         
            throw e;
        }
    }     
    
    /**
     * liste des réservations en Attente
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String listeReservations(HttpServletRequest request) throws Exception {
        ReservationDao reservationDao = new ReservationDao();
        try {
            List<Reservation> lstReservation =  reservationDao.liste();
            HttpSession session = request.getSession(true);
            request.setAttribute("lstReservationsR", lstReservation);

            
            return ("/listereservations.jsp");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Enregistre une réservation et la met en Attente
     * @param request
     * @return
     * @throws Exception
     */
    private String enregistrerReservation(HttpServletRequest request) throws Exception {
        
        
            String vueReponse = "/catalogue.jsp";
        int id_oeuvre = 0;
        int id_adherent = 0;
        java.util.Date date = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yy-mm-dd");
        ReservationDao reservationDao = new ReservationDao();
        try {

            Reservation reservation = new Reservation();
            
            
            if (request.getParameter("txtDate") != "") {
                date = DATE_FORMAT.parse(request.getParameter("txtDate"));
            }
            reservation.setDate_reservation(date);
            
            if (request.getParameter("lstAdherents") != "") {
                id_adherent = Integer.parseInt(request.getParameter("lstAdherents"));
            }
            reservation.setId_adherent(id_adherent);
            
            if (request.getParameter("id") != "") {
                id_oeuvre = Integer.parseInt(request.getParameter("id"));
            }
            reservation.setId_oeuvre(id_oeuvre);
             
            reservation.setStatut("attente");
                reservationDao.ajouter(reservation);
//            List<Oeuvre> lstOeuvre = oeuvreDo.liste();
//            HttpSession session = request.getSession(true);
//            request.setAttribute("lstOeuvresR", lstOeuvre);
//            return (vueReponse);
               List<Reservation> lstReservation = reservationDao.liste();
               HttpSession session = request.getSession(true);
               request.setAttribute("lstReservationsR", lstReservation);
            return ("listeReservations.res");}
         catch (Exception e) {
            erreur = e.getMessage();
            if(erreur.contains("PRIMARY"))
                erreur = "L'oeuvre " /*+ titre */+ " a déjà été réservée pour le : " + /*date + */" !";            
            throw new Exception(erreur);
        }
        
    }

    /**
     * Lit une oeuvre, l'affiche et initialise la liste des adhérents
     * pour pouvoir saisir une réservation :
     * Saisie date et sélection de l'adhérent
     * @param request
     * @return
     * @throws Exception
     */
    private String reserverOeuvre(HttpServletRequest request) throws Exception {
        AdherentDao adherentDao = new AdherentDao();
        OeuvreDao oeuvreDao = new OeuvreDao();
        try {
            
           ReservationDao reservationDao = new ReservationDao();
           Oeuvre oeuvre = oeuvreDao.lire_Id(Integer.parseInt(request.getParameter("id")));
           List<Adherent> lstAdherent = adherentDao.liste();
           HttpSession session = request.getSession(true);
           request.setAttribute("lstAdherentsR", lstAdherent);
           request.setAttribute("oeuvreR", oeuvre);      
            return ("/reservation.jsp");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Extrait le texte de la demande de l'URL
     * @param request
     * @return String texte de la demande
     */
    private String getDemande(HttpServletRequest request) {
        String demande = "";
        demande = request.getRequestURI();
        demande = demande.substring(demande.lastIndexOf("/") + 1);
        return demande;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
