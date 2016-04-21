/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modeles.Oeuvre;
import modeles.Reservation;

/**
 *
 * @author Epulapp
 */
public class ReservationDao extends Dao {
    
        public List<Reservation> liste() throws Exception {
        // Dictionnaire de paramètres pour la
        // requête SQL, ici il sera vide, mais
        // la méthode lecture() attend ce paramètre
        Map mParams = new HashMap();
        Map mParam = new HashMap();
        mParams.put(0, mParam);
        // Dictionnaire recevant les enregistrements lus
        Map mResults;
        // La collection de User à retourner
        List<Reservation> lReservation = new ArrayList<Reservation>();
        try {
            String requete = "select * from reservation";
            // Lecture des User dans la BdD
            mResults = lecture(requete, mParams);
            // Chaque enregistrement est référencé par un indice
            // Pour chaque item du Dictionnaire
            for (Object record :mResults.keySet()) {
                // On récuprère le Dictionnaire de l'enregistrement
                Map mRecord = (Map)mResults.get(record);
                // On l'envoie pour affectation des données portées
                // à l'objet Métier User qui ajouté à la Collection
                lReservation.add(setProperties(mRecord));
            }
            return (lReservation);
        } catch (Exception e) {
            throw e;
        } 
    }
    
        
        
        
    private Reservation setProperties(Map mRecord) throws Exception {
        Reservation reservation = new Reservation();
        try {
            // Le nom de la colonne est la clé permettant de
            // récupérer la valeur correspondante
            reservation.setDate_reservation(((java.util.Date)(mRecord.get("date_reservation"))));
            reservation.setId_adherent(((Integer)(mRecord.get("id_adherent"))));
            reservation.setId_oeuvre(((Integer)(mRecord.get("id_oeuvre"))));
            reservation.setStatut(mRecord.get("statut").toString());
            // Il faut aller chercher la catégorie du User
            OeuvreDao oeuvreDao = new OeuvreDao();
            reservation.setOeuvre(oeuvreDao.lire_Id(reservation.getId_oeuvre()));
            AdherentDao adherentDao = new AdherentDao();
            reservation.setAdherent(adherentDao.lire_Id(reservation.getId_adherent()));
            
            
            return reservation;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void supprimer (Reservation reservation) throws Exception
    {
        Map mParams = new HashMap();
        Map mParam;
        try
        {
            String requete = "delete from reservation where date_reservation = ? && id_oeuvre = ?";
            mParam = new HashMap();
            mParam.put(1,new java.sql.Date(reservation.getDate_reservation().getTime()));
            mParam.put(2, reservation.getId_oeuvre());
            mParams.put(0, mParam);
            ecriture(requete, mParams);
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    
    public void modifier (Reservation reservation) throws Exception
    {
        Map mParams = new HashMap();
        Map mParam;
        try
        {
            String requete = "update reservation set statut = ? where date_reservation = ? && id_oeuvre = ?";
            mParam = new HashMap();
            mParam.put(1, reservation.getStatut());
            mParam.put(2,new java.sql.Date(reservation.getDate_reservation().getTime()));
            mParam.put(3, reservation.getId_oeuvre());
            mParams.put(0, mParam);
            ecriture(requete, mParams);
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    
    public Reservation lire_date(java.util.Date date,int id_oeuvre) throws Exception {

        Map mParams = new HashMap();
        Map mParam;
        Map mResults;
        try {
            String requete = "select * from reservation where date_reservation = ? && id_oeuvre = ?";
            mParam = new HashMap();
            mParam.put(1, new java.sql.Date(date.getTime()));
            mParam.put(2, id_oeuvre);
            mParams.put(0, mParam);
            mResults = lecture(requete, mParams);
            if (mResults.size() > 0) {
                Map mRecord = (Map) mResults.get(0);
                return (setProperties(mRecord));
            } else {
                throw new Exception("reservation inconnue !");
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void ajouter(Reservation reservation) throws Exception {
        // Dictionnaire des paramÃ¨tres qui seront
        // affectÃ©s Ã  la requÃªte        
        Map mParams = new HashMap();
        Map mParam;
        try {
            String requete = "insert into reservation (date_reservation, id_oeuvre, id_adherent, statut )";
            requete += " values (?, ?, ?, ?)";
            // On ajoute chaque paramÃ¨tre au Dictionnaire
            // en spÃ©cifiant sa place dans la requÃªte 
            mParam = new HashMap();
            mParam.put(1,new java.sql.Date(reservation.getDate_reservation().getTime()));
            mParam.put(2, reservation.getId_oeuvre());
            mParam.put(3, reservation.getId_adherent());
            mParam.put(4, reservation.getStatut());
            mParams.put(0, mParam);
            List<String> lRequetes = new ArrayList<String>();
            // On utilise une collection de requÃªtes car c'est
            // ce qu'attend la mÃ©thode transaction() en paramÃ¨tre
            lRequetes.add(requete);
            // Le troisiÃ¨me paramÃ¨tre servira Ã  gÃ©nÃ©rer
            // l'Id du nouvel Utilisateur
            transaction(lRequetes, mParams, "RESERVATION");
        } catch (Exception e) {
            throw e;
        }
    }
}
