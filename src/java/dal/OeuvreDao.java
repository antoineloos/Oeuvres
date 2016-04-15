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
import modeles.Proprietaire;

/**
 *
 * @author Epulapp
 */
public class OeuvreDao extends Dao {

    public List<Oeuvre> liste() throws Exception {
        // Dictionnaire de paramètres pour la
        // requête SQL, ici il sera vide, mais
        // la méthode lecture() attend ce paramètre
        Map mParams = new HashMap();
        Map mParam = new HashMap();
        mParams.put(0, mParam);
        // Dictionnaire recevant les enregistrements lus
        Map mResults;
        // La collection de User à retourner
        List<Oeuvre> lOeuvres = new ArrayList<Oeuvre>();
        try {
            String requete = "select * from oeuvre";
            // Lecture des User dans la BdD
            mResults = lecture(requete, mParams);
            // Chaque enregistrement est référencé par un indice
            // Pour chaque item du Dictionnaire
            for (Object record : mResults.keySet()) {
                // On récuprère le Dictionnaire de l'enregistrement
                Map mRecord = (Map) mResults.get(record);
                // On l'envoie pour affectation des données portées
                // à l'objet Métier User qui ajouté à la Collection
                lOeuvres.add(setProperties(mRecord));
            }
            return (lOeuvres);
        } catch (Exception e) {
            throw e;
        }
    }

    private Oeuvre setProperties(Map mRecord) throws Exception {
        Oeuvre oeuvre = new Oeuvre();
        try {
            // Le nom de la colonne est la clé permettant de
            // récupérer la valeur correspondante
            oeuvre.setId_oeuvre(((Integer) (mRecord.get("id_oeuvre"))));
            oeuvre.setId_proprietaire(((Integer) (mRecord.get("id_proprietaire"))));
            oeuvre.setPrix(Double.parseDouble(mRecord.get("prix").toString()));
            oeuvre.setTitre(mRecord.get("titre").toString());

            // Il faut aller chercher la catégorie du User
            UserDao userDao = new UserDao();
            oeuvre.setProprietaire(userDao.lire_Id(oeuvre.getId_proprietaire()));
            return oeuvre;
        } catch (Exception e) {
            throw e;
        }
    }

    public void ajouter(Oeuvre oeuvre) throws Exception {
        // Dictionnaire des paramÃ¨tres qui seront
        // affectÃ©s Ã  la requÃªte        
        Map mParams = new HashMap();
        Map mParam;
        try {
            String requete = "insert into oeuvre (titre, prix, id_proprietaire, id_oeuvre)";
            requete += " values (?, ?, ?, :id)";
            // On ajoute chaque paramÃ¨tre au Dictionnaire
            // en spÃ©cifiant sa place dans la requÃªte 
            mParam = new HashMap();
            mParam.put(1, oeuvre.getTitre());
            mParam.put(2, oeuvre.getPrix());
            mParam.put(3, oeuvre.getId_proprietaire());
            mParams.put(0, mParam);
            List<String> lRequetes = new ArrayList<String>();
            // On utilise une collection de requÃªtes car c'est
            // ce qu'attend la mÃ©thode transaction() en paramÃ¨tre
            lRequetes.add(requete);
            // Le troisiÃ¨me paramÃ¨tre servira Ã  gÃ©nÃ©rer
            // l'Id du nouvel Utilisateur
            transaction(lRequetes, mParams, "OEUVRE");
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void supprimer(int id) throws Exception {
        // Dictionnaire des paramÃ¨tres qui seront
        // affectÃ©s Ã  la requÃªte        
        Map mParams = new HashMap();
        Map mParam;
        try {
            String requete = "delete from oeuvre where id_oeuvre = ?";
            // On ajoute chaque paramÃ¨tre au Dictionnaire
            // en spÃ©cifiant sa place dans la requÃªte 
            mParam = new HashMap();
            mParam.put(1, id);
            mParams.put(0, mParam);
            List<String> lRequetes = new ArrayList<String>();
            // On utilise une collection de requÃªtes car c'est
            // ce qu'attend la mÃ©thode transaction() en paramÃ¨tre
            lRequetes.add(requete);
            // Le troisiÃ¨me paramÃ¨tre servira Ã  gÃ©nÃ©rer
            // l'Id du nouvel Utilisateur
            transaction(lRequetes, mParams, "OEUVRE");
        } catch (Exception e) {
            throw e;
        }
    }

    public Oeuvre lire_Id(int id) throws Exception {

        Map mParams = new HashMap();
        Map mParam;
        Map mResults;
        try {
            String requete = "select * from oeuvre where id_oeuvre = ?";
            mParam = new HashMap();
            mParam.put(1, id);
            mParams.put(0, mParam);
            mResults = lecture(requete, mParams);
            if (mResults.size() > 0) {
                Map mRecord = (Map) mResults.get(0);
                return (setProperties(mRecord));
            } else {
                throw new Exception("Oeuvre inconnue !");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void modifier(Oeuvre oeuvre) throws Exception {
        Map mParams = new HashMap();
        Map mParam;
        try {
            String requete = "update oeuvre set titre = ?, prix = ?, id_proprietaire = ? where id_oeuvre = ?";
            mParam = new HashMap();
            mParam.put(1, oeuvre.getTitre());
            mParam.put(2, oeuvre.getPrix());
            mParam.put(3, oeuvre.getId_proprietaire());
            mParam.put(4, oeuvre.getId_oeuvre());
            mParams.put(0, mParam);
            ecriture(requete, mParams);
        } catch (Exception e) {
            throw e;
        }
    }
    
    

}
