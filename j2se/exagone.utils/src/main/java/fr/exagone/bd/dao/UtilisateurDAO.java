package fr.exagone.bd.dao;

import fr.exagone.bd.entity.UtilisateurEntity;
import fr.exagone.bd.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

public class UtilisateurDAO {

    private static final Log LOG = LogFactoryImpl.getLog(UtilisateurDAO.class);

    public UtilisateurEntity fromResultSet(ResultSet rs) throws SQLException {
        UtilisateurEntity user = null;
        if (rs != null && rs.next()) {
            user = new UtilisateurEntity();
            user.setId(rs.getInt("id"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setPwd(rs.getString("password"));
        }
        return user;
    }

    public UtilisateurEntity findUtilisateur(Integer id, Connection connexion) {
        UtilisateurEntity user = null;

        String sql = "Select id, nom, prenom, login, password from Utilisateur where id = ?";
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
                    LOG.trace("tentative de f   ermeture de la connexion à partir du DAO");
                    ConnectionUtil.getInstance().fermerConnexion();
                } catch (SQLException e) {}
            }
        }
        return user;
    }

}
