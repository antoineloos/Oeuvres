/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        try {

            // On ajoute chaque paramètre au Dictionnaire
            // en spécifiant sa place dans la requête 
            // On utilise une collection de requêtes car c'est
            // ce qu'attend la méthode transaction() en paramètre
            // Le troisième paramètre servira à générer
            // l'Id du nouvel Utilisateur
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

        try {

        } catch (Exception e) {
            throw e;
        }
        return null;
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

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = super.connecter();
            ps = connection.prepareStatement(
                    "select * from proprietaire where login = ?");
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
               return setProperties(rs);
                
            } else {
                throw new Exception("Utilisateur inconnu !");

            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            lire_Login(login);
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
    private Proprietaire setProperties(ResultSet mRecord) throws Exception {

        try {
            
             user.setId_proprietaire(mRecord.getInt("id_proprietaire"));
                user.setNom_proprietaire(mRecord.getString("nom_proprietaire"));
                user.setPrenom_proprietaire(mRecord.getString("prenom_proprietaire"));
                user.setLogin(mRecord.getString("login"));
                user.setPwd(mRecord.getString("pwd"));
             
            return user;
        } catch (Exception e) {
            throw e;
        }
    }

}
