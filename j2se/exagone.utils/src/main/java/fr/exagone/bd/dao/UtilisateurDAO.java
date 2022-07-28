package fr.exagone.bd.dao;

import fr.exagone.bd.entity.CompteEntity;
import fr.exagone.bd.entity.ESex;
import fr.exagone.bd.entity.UtilisateurEntity;
import fr.exagone.bd.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.plaf.nimbus.State;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

public class UtilisateurDAO {

    private static final Log LOG = LogFactoryImpl.getLog(UtilisateurDAO.class);

    public UtilisateurEntity insert(UtilisateurEntity utilisateur, Connection connection) {

        String sql = "INSERT INTO Utilisateur(login, password, nom, prenom, sex, dateDeNaissance, adresse, codePostal, telephone) " +
                "VALUES (? ,?, ?, ?, ?, ? ,?, ?, ?)";

        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean bCreatedConnexion = false;
        boolean doCommit = false;

        try {
            if (connection == null) {
                connection = ConnectionUtil.getInstance().etablirConnexion();
                bCreatedConnexion = true;
            }
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, utilisateur.getLogin());
            ps.setString(2, utilisateur.getPwd());
            ps.setString(3, utilisateur.getNom());
            ps.setString(4, utilisateur.getPrenom());
            if (ESex.HOMME.equals(utilisateur.getSex())) {
                ps.setByte(5, (byte) 0);
            } else if (ESex.FEMME.equals(utilisateur.getSex())) {
                ps.setByte(5, (byte)1);
            } else {
                ps.setByte(5, (byte)2);
            }
            ps.setDate(6, utilisateur.getDateDeNaissance());
            ps.setString(7, utilisateur.getAdresse());
            if (utilisateur.getCodePostal() != null) {
                ps.setInt(8, utilisateur.getCodePostal());
            } else {
                ps.setNull(8, Types.INTEGER);
            }
            ps.setString(9, utilisateur.getTelephone());
            int nbRow = ps.executeUpdate();
            if (nbRow > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    utilisateur.setId(rs.getInt(1));
                }
            }
            ps.close();
            doCommit = true;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            handleTransaction(connection, ps, rs, bCreatedConnexion, doCommit);
        }
        return utilisateur;

    }

    public UtilisateurEntity update(UtilisateurEntity utilisateur, Connection connexion) {

        String sql = "UPDATE Utilisateur " +
                "SET nom=?,prenom=?,login=?,password=?,sex=?,derniereConnection=?, " +
                "dateDeNaissance=?,adresse=?,codePostal=?,telephone=? " +
                "WHERE id = ?";

        PreparedStatement ps = null;
        boolean bCreatedConnexion = false;
        boolean doCommit = false;

        try {
            if (connexion == null) {
                connexion = ConnectionUtil.getInstance().etablirConnexion();
                bCreatedConnexion = true;
            }
            connexion.setAutoCommit(false);
            ps = connexion.prepareStatement(sql);
            ps.setString(1, utilisateur.getPrenom());
            ps.setString(2, utilisateur.getPrenom());
            ps.setString(3, utilisateur.getLogin());
            ps.setString(4, utilisateur.getPwd());
            if (utilisateur.getSex().equals(ESex.HOMME)) {
                ps.setByte(5, (byte)0);
            } else if(utilisateur.getSex().equals(ESex.FEMME)) {
                ps.setByte(5, (byte)1);
            } else {
                ps.setByte(5, (byte)2);
            }
            ps.setTimestamp(6, utilisateur.getDerniereConnection());
            ps.setDate(7, utilisateur.getDateDeNaissance());
            ps.setString(8, utilisateur.getAdresse());
            if (utilisateur.getCodePostal() != null) {
                ps.setInt(9, utilisateur.getCodePostal());
            } else {
                ps.setNull(9, Types.INTEGER);
            }
            ps.setString(10, utilisateur.getTelephone());
            ps.setInt(11, utilisateur.getId());

            int nbRowUpdated = ps.executeUpdate();
            LOG.trace("[Modification utilisateur] - id = " + utilisateur.getId() + " - nbRow = " + nbRowUpdated);
            doCommit = nbRowUpdated == 1;

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            handleTransaction(connexion, ps, null, bCreatedConnexion, doCommit);
        }
        return utilisateur;
    }

    public boolean delete(UtilisateurEntity utilisateur, Connection connexion) {
        String sql = "DELETE FROM Utilisateur WHERE id = ?";
        PreparedStatement ps = null;
        boolean bCreatedConnexion = false;
        boolean doCommit = false;
        try {
            if (connexion == null) {
                connexion = ConnectionUtil.getInstance().etablirConnexion();
                bCreatedConnexion = true;
            }
            connexion.setAutoCommit(false);
            ps = connexion.prepareStatement(sql);
            ps.setInt(1, utilisateur.getId());
            int nbRow = ps.executeUpdate();
            LOG.trace("[Suppression utilisateur] : Nombre de ligne supprimée : " + nbRow);
            doCommit = nbRow == 1;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            handleTransaction(connexion, ps, null, bCreatedConnexion, doCommit);
        }
        return doCommit;
    }

    public UtilisateurEntity find(Integer id, Connection connexion) {
        UtilisateurEntity user = null;

        String sql = "Select id, nom, prenom, login, password, sex, derniereConnection, dateDeNaissance, adresse, codePostal, telephone from Utilisateur where id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean bCreatedConnexion = false;

        try {
            if (connexion == null) {
                LOG.trace("tentative ouverture de la connexion à partir du DAO");
                connexion = ConnectionUtil.getInstance().etablirConnexion();
                bCreatedConnexion = true;
            }
            ps = connexion.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            user = fromResultSet(rs);
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (connexion != null && bCreatedConnexion) {
                try {
                    LOG.trace("tentative de fermeture de la connexion à partir du DAO");
                    ConnectionUtil.getInstance().fermerConnexion();
                } catch (SQLException e) {}
            }
        }
        return user;
    }

    private void handleTransaction(Connection connexion, Statement ps, ResultSet rs,
            boolean bCreatedConnexion, boolean doCommit) {
        if (connexion != null) {
            try {
                if (doCommit) {
                    if (!connexion.getAutoCommit()) {
                        connexion.commit();
                    }
                }
                else {
                    connexion.rollback();
                }
            } catch (SQLException e) {}
        }
        if (rs != null)  {
            try {
                rs.close();
            } catch(SQLException sqle) {}
        }
        if (ps != null) {
            try {
                ps.close();
            } catch(SQLException sqle){}
        }
        if (connexion != null &&  bCreatedConnexion) {
            try {
                connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public UtilisateurEntity fromResultSet(ResultSet rs) throws SQLException {
        UtilisateurEntity user = null;
        if (rs != null && rs.next()) {
            user = new UtilisateurEntity();
            user.setId(rs.getInt("id"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setLogin(rs.getString("login"));
            user.setPwd(rs.getString("password"));
            byte sex = rs.getByte("sex");
            switch (sex) {
                case 0 :
                    user.setSex(ESex.HOMME);
                    break;
                case 1 :
                    user.setSex(ESex.FEMME);
                    break;
                default:
                    user.setSex(ESex.AUTRE);
            }
            user.setDerniereConnection(rs.getTimestamp("derniereConnection"));
            user.setAdresse(rs.getString("adresse"));
            int cp = rs.getInt("codePostal");
            if (rs.wasNull()) {
                user.setCodePostal(null);
            } else {
                user.setCodePostal(cp);
            }
            user.setTelephone(rs.getString("telephone"));
            user.setDateDeNaissance(rs.getDate("dateDeNaissance"));
        }
        return user;
    }

}
