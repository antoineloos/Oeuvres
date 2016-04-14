/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import modeles.Proprietaire;

/**
 *
 * @author Epulapp
 */
public class UserDao extends Dao {

    public UserDao() {
    }
    // Objet Métier porteur des propriétés
    private Proprietaire user;

    public Proprietaire getUser() {
        return this.user;
    }

    /**
     * Liste des Utilisateurs
     *
     * @return Collection de User
     * @throws Exception
     */
//    public List<Proprietaire> liste() throws Exception {
//        // Dictionnaire de paramètres pour la
//        // requête SQL, ici il sera vide, mais
//        // la méthode lecture() attend ce paramètre
//
//        // Dictionnaire recevant les enregistrements lus
//        // La collection de User à retourner
//        try {
//
//            // Lecture des User dans la BdD
//            // Chaque enregistrement est référencé par un indice
//            // Pour chaque item du Dictionnaire
//            // On récuprère le Dictionnaire de l'enregistrement
//            // On l'envoie pour affectation des données portées
//            // à l'objet Métier User qui ajouté à la Collection
//        }
//        return (lUtilisateurs);
//        catch (Exception e) {
//            throw e;
//        }
//    }

    
    public List<Proprietaire> liste() throws Exception {
        // Dictionnaire de paramètres pour la
        // requête SQL, ici il sera vide, mais
        // la méthode lecture() attend ce paramètre
        Map mParams = new HashMap();
        Map mParam = new HashMap();
        mParams.put(0, mParam);
        // Dictionnaire recevant les enregistrements lus
        Map mResults;
        // La collection de User à retourner
        List<Proprietaire> lProprietaire = new ArrayList<Proprietaire>();
        try {

            String requete = "select * from proprietaire";
            // Lecture des User dans la BdD
            mResults = lecture(requete, mParams);
            // Chaque enregistrement est référencé par un indice
            // Pour chaque item du Dictionnaire
            for (Object record :mResults.keySet()) {
                // On récuprère le Dictionnaire de l'enregistrement
                Map mRecord = (Map)mResults.get(record);
                // On l'envoie pour affectation des données portées
                // à l'objet Métier User qui ajouté à la Collection
                lProprietaire.add(setProperties(mRecord));
            }
            return (lProprietaire);
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
    public void modifier(Proprietaire user) throws Exception {
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
    public void ajouter(Proprietaire user) throws Exception {
        // Dictionnaire des paramètres qui seront
        // affectés à la requête        

        Map mParams = new HashMap();
        Map mParam;
        try {
            String requete = "insert into utilisateur (nom, prenom, login, pwd, id_utilisateur)";
            requete += " values (?, ?, ?, ?, ?, :id)";
            // On ajoute chaque paramètre au Dictionnaire
            // en spécifiant sa place dans la requête 
            mParam = new HashMap();
            mParam.put(1, user.getNom_proprietaire());
            mParam.put(2, user.getPrenom_proprietaire());
            mParam.put(3, user.getLogin());
            mParam.put(4, user.getPwd());
            mParams.put(0, mParam);
            List<String> lRequetes = new ArrayList<String>();
            // On utilise une collection de requêtes car c'est
            // ce qu'attend la méthode transaction() en paramètre
            lRequetes.add(requete);
            // Le troisième paramètre servira à générer
            // l'Id du nouvel Utilisateur
            transaction(lRequetes, mParams, "PROPRIETAIRE");
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
    public Proprietaire lire_Id(int id) throws Exception {

        Map mParams = new HashMap();
        Map mParam;
        Map mResults;
        try {
            String requete = "select * from proprietaire where id_proprietaire = ?";
            mParam = new HashMap();
            mParam.put(1, id);
            mParams.put(0, mParam);            
            mResults = lecture(requete, mParams);
            if (mResults.size() > 0) {
                Map mRecord = (Map)mResults.get(0);
                return(setProperties(mRecord));
            }  else {
                throw new Exception("Proprietaire inconnu !");
            }
        } catch (Exception e) {
            throw e;
        } 
    }

    /**
     * Lecture d'un Utilisateur sur son login Notes : le login est unique :
     * fonctionnement identique aux autres méthodes
     *
     * @param login Login de l'utilisateur à lire
     * @return objet Métier Proprietaire
     * @throws Exception
     */
    public Proprietaire lire_Login(String login) throws Exception {
        Map mParams = new HashMap();
        Map mParam;
        Map mResults;
        try {
            String requete = "select * from proprietaire where login = ?";
            mParam = new HashMap();
            mParam.put(1, login);
            mParams.put(0, mParam);
            mResults = lecture(requete, mParams);
            if (mResults.size() > 0) {
                Map mRecord = (Map)mResults.get(0);
                return(setProperties(mRecord));
            } else {
                throw new Exception("Utilisateur inconnu !");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Vérifie qu'un Utilisateur voulant s'authentifier a bien fournit le bon
     * login et le bon mot de passe
     *
     * @param login Login de l'Utilisateur
     * @param pwd Mot de passe de l'Utilisateur
     * @return True : si tout OK, False sinon
     * @throws Exception
     */
    public boolean connecter(String login, String pwd) throws Exception {

        try {
            boolean retour = false;
            this.user =  lire_Login(login);
            if (pwd.equals(user.getPwd())) {
                retour = true;
            }
            return retour;
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
    private Proprietaire setProperties(Map mRecord) throws Exception {
            Proprietaire user;
        try {
                user = new Proprietaire();
                user.setId_proprietaire((Integer)mRecord.get("id_proprietaire"));
                user.setNom_proprietaire(mRecord.get("nom_proprietaire").toString());
                user.setPrenom_proprietaire(mRecord.get("prenom_proprietaire").toString());
                user.setLogin(mRecord.get("login").toString());
                user.setPwd(mRecord.get("pwd").toString());
             
            return user;
        } catch (Exception e) {
            throw e;
        }
    }

}
