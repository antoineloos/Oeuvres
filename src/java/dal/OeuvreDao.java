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
            for (Object record :mResults.keySet()) {
                // On récuprère le Dictionnaire de l'enregistrement
                Map mRecord = (Map)mResults.get(record);
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
            oeuvre.setId_oeuvre(((Integer)(mRecord.get("id_oeuvre"))));
            oeuvre.setId_proprietaire(((Integer)(mRecord.get("id_proprietaire"))));
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
}
