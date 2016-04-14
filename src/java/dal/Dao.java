/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Epulapp
 */
public abstract class Dao {

    /**
     * Etablit une connexion à la base de données en prenant les paramètres de
     * connexion dans un fichier properties
     *
     * @return Connexion à la base de données
     * @throws Exception
     */
    protected Connection connecter() throws Exception {
        Context initCtx, envCtx;
        DataSource ds;
        Connection connection;
        try {
            initCtx = new InitialContext();
            envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/Oeuvres");
            connection = ds.getConnection();
            return (connection);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Récupère une clé primaire fiable dans la BdD en appelant une procédure
     * stockée qui incrémente une valeur prise dans la table CLES
     *
     * @param connection Connexion en cours à la BdD
     * @param id Nom de la table dont on veut générer la PK
     * @return valeur de la PK
     * @throws Exception
     */
    private int getIdentifiant(Connection connection, String id) throws Exception {
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{? = call inc_parametre(?)}");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, id);
            cs.execute();
            return (cs.getInt(1));
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (Exception e) {
                throw e;
            }
        }
    }

    /**
     * Effecture des accès en écriture dans la BdD dans le cadre d'une
     * transaction. Valide la transaction si tout s'est bien passé, sinon
     * l'invalide Note : il peut y avoir plusieurs requêtes d'insertion, de mise
     * à jour ou de suppression
     *
     * @param lRequetes Collection de requêtes à exécuter
     * @param mParams Dictionnaire de Dictionnaire de paramètres de requêtes
     * @param cle Le nom de la table dont on veut générer la PK
     * @throws Exception
     */
    protected void transaction(List<String> lRequetes, Map mParams, String cle) throws Exception {
        PreparedStatement ps = null;
        Connection connection = null;
        int cptParam = 0;
        try {
            connection = connecter();
            connection.setAutoCommit(false);
            for (String requete : lRequetes) {
                if (requete.contains(":id")) {
                    int id = getIdentifiant(connection, cle);
                    requete = requete.replace(":id", "" + id);
                }
                ps = connection.prepareStatement(requete);
                setParametres(ps, (Map) mParams.get(cptParam++));
                ps.executeUpdate();
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            try {

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
     * Méthode permettant de mettre à jour la BdD sans avoir recours à une
     * transaction
     *
     * @param requete Requête à exécuter
     * @param mParams Dictionnaire de dictionnaire de paramètres
     * @throws Exception
     */
    protected void ecriture(String requete, Map mParams) throws Exception {
        PreparedStatement ps  = null;
        Connection connection = null;
        
        try {
            connection = connecter();
            ps = connection.prepareStatement(requete);
            setParametres(ps,(Map)mParams.get(0));
            ps.executeUpdate();
                    
            
            
            
        } catch (Exception e) {
            throw e;
        } finally {
            try {
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
     * Extraction de données de la BdD
     *
     * @param requete requête d'extraction
     * @param mParams Dictionnaire de Dictionnaire de paramètres
     * @return Dictionnaire avec en clé le nom des colonne et en valeur les
     * données
     * @throws Exception
     */
    protected Map lecture(String requete, Map mParams) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        Map mRecord;
        Map mResults = new HashMap();
        try {
            connection = connecter();
            ps = connection.prepareStatement(requete);
            setParametres(ps, (Map) mParams.get(0));
            rs = ps.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            int nbColonnes = rsm.getColumnCount();
            int cptRecord = 0;
            while (rs.next()) {
                mRecord = new HashMap();
                for (int i = 1; i <= nbColonnes; i++) {
                    String nomColonne = rsm.getColumnName(i).toLowerCase();
                    mRecord.put(nomColonne, rs.getObject(rsm.getColumnName(i)));
                }
                mResults.put(cptRecord++, mRecord);
            }

            return (mResults);
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
     * Affecte les paramètres du Dictionnaire aux paramètres du
     * PreparedStatement La key du dictionnaire représente la position du
     * paramètre dans la requête La value est un objet dont on recherche le Type
     * et selon ce type on transtypera la valeur à déposer
     *
     * @param ps PreparedStatement dont on veut alimenter les paramètres
     * @param mParam Dictionnaire des valeurs à déposer
     * @return PreparedStatement affecté des valeurs
     * @throws Exception
     */
    private PreparedStatement setParametres(PreparedStatement ps, Map mParam) throws Exception {
        String classe;
        for(Object indice : mParam.keySet())
        {
            classe  = mParam.get(indice).getClass().toString();
            if(classe.contains("Integer"))
            {
                ps.setInt((Integer) indice, (Integer) mParam.get(indice));
            }
            else if (classe.contains("String"))
            {
                ps.setString((Integer) indice, (String) mParam.get(indice));
            }
            else if (classe.contains("Double"))
            {
                ps.setDouble((Integer) indice,(Double) mParam.get(indice));
            }
            else if (classe.contains("Date"))
            {
                ps.setDate((Integer) indice, (Date) mParam.get(indice));
            }
        }
        return ps;
    }
}
