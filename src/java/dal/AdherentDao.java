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
import modeles.Adherent;
import modeles.Proprietaire;

/**
 *
 * @author Epulapp
 */
public class AdherentDao extends Dao {
    public AdherentDao() {
    }
    // Objet Métier porteur des propriétés
    private Adherent adherent;

    public Adherent getAdherent() {
        return this.adherent;
    }


    
    public List<Adherent> liste() throws Exception {
        // Dictionnaire de paramètres pour la
        // requête SQL, ici il sera vide, mais
        // la méthode lecture() attend ce paramètre
        Map mParams = new HashMap();
        Map mParam = new HashMap();
        mParams.put(0, mParam);
        // Dictionnaire recevant les enregistrements lus
        Map mResults;
        // La collection de User à retourner
        List<Adherent> lAdherent = new ArrayList<Adherent>();
        try {

            String requete = "select * from adherent";
            // Lecture des User dans la BdD
            mResults = lecture(requete, mParams);
            // Chaque enregistrement est référencé par un indice
            // Pour chaque item du Dictionnaire
            for (Object record :mResults.keySet()) {
                // On récuprère le Dictionnaire de l'enregistrement
                Map mRecord = (Map)mResults.get(record);
                // On l'envoie pour affectation des données portées
                // à l'objet Métier User qui ajouté à la Collection
                lAdherent.add(setProperties(mRecord));
            }
            return (lAdherent);
        }
        catch (Exception e) {
            throw e;
        }
    }
    /**
     * Mise à jour d'un Utilisateur dans la BdD
     *
     * @param user Objet Métier portant les données
     * @throws Exception
     */
    public void modifier(Adherent adherent) throws Exception {
        // Dictionnaire des paramètres qui seront
        // affectés à la requête

        try {

            // On ajoute chaque paramètre au Dictionnaire
            // en spécifiant sa place dans la requête
            // Mise à jour dans la BdD
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Ajoute un Utilisateur dans la BdD Note : On utilise un Dictionnaire de
     * Dictionnaire car la méthode transaction() exécutera une collection de
     * requêtes qui auront chacune leur Dictionnaire de paramètres
     *
     * @param user Objet Métier portant les données
     * @throws Exception
     */
    public void ajouter(Adherent adherent) throws Exception {
        // Dictionnaire des paramètres qui seront
        // affectés à la requête        

        Map mParams = new HashMap();
        Map mParam;
        try {
            String requete = "insert into utilisateur (nom_adherent, prenom_adherent, id_adherent)";
            requete += " values (?, ?, :id)";
            // On ajoute chaque paramètre au Dictionnaire
            // en spécifiant sa place dans la requête 
            mParam = new HashMap();
            mParam.put(1, adherent.getNom_adherent());
            mParam.put(2, adherent.getPrenom_adherent());
            mParams.put(0, mParam);
            List<String> lRequetes = new ArrayList<String>();
            // On utilise une collection de requêtes car c'est
            // ce qu'attend la méthode transaction() en paramètre
            lRequetes.add(requete);
            // Le troisième paramètre servira à générer
            // l'Id du nouvel Utilisateur
            transaction(lRequetes, mParams, "ADHERENT");
        } catch (Exception e) {
            throw e;
        } 
    }

    /**
     * Lecture d'un Utilisateur sur son Id Note : fonctionnement identique aux
     * autres méthodes
     *
     * @param id Identifiant de l'Utilisateur à lire
     * @return un objet Métier User
     * @throws Exception
     */
    public Adherent lire_Id(int id) throws Exception {

        Map mParams = new HashMap();
        Map mParam;
        Map mResults;
        try {
            String requete = "select * from adherent where id_adherent = ?";
            mParam = new HashMap();
            mParam.put(1, id);
            mParams.put(0, mParam);            
            mResults = lecture(requete, mParams);
            if (mResults.size() > 0) {
                Map mRecord = (Map)mResults.get(0);
                return(setProperties(mRecord));
            }  else {
                throw new Exception("adherent inconnu !");
            }
        } catch (Exception e) {
            throw e;
        } 
    }

    
   

    /**
     * Affecte les valeur prises dans le dictionnaire au propriétés de l'objet
     * Métier Proprietaire
     *
     * @param mRecord Dictionnaire contenant les valeurs lues dans la BdD
     * @return objet Métier Proprietaire
     * @throws Exception
     */
    private Adherent setProperties(Map mRecord) throws Exception {
            Adherent adherent;
        try {
                adherent = new Adherent();
                adherent.setId_adherent((Integer)mRecord.get("id_adherent"));
                adherent.setNom_adherent(mRecord.get("nom_adherent").toString());
                adherent.setPrenom_adherent(mRecord.get("prenom_adherent").toString());
                
             
            return adherent;
        } catch (Exception e) {
            throw e;
        }
    }
}
