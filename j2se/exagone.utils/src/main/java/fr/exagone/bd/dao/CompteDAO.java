package fr.exagone.bd.dao;

import fr.exagone.bd.entity.CompteEntity;
import fr.exagone.bd.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

public class CompteDAO {

    private static final Log LOG = LogFactoryImpl.getLog(CompteDAO.class);

    public CompteEntity fromResultSet(ResultSet rs) throws SQLException {
        CompteEntity compteEntity = null;
        if (rs != null && rs.next()) {
            compteEntity = new CompteEntity();
            compteEntity.setId(rs.getInt("id"));
            compteEntity.setSolde(rs.getInt("solde"));
            compteEntity.setLibelle(rs.getString("libelle"));
            compteEntity.setUtilisateurId(rs.getInt("utilisateurId"));
        }
        return  compteEntity;
    }

    /*
    public CompteEntity insert(CompteEntity compte) {

        String sql = "INSERT INTO Compte(id, ) VALUES (? ,?, ?, ?)"


        return compte;
    }
    */

    public CompteEntity find(Integer id, Connection connexion) {

        CompteEntity compteEntity = null;

        String sql = "SELECT id, libelle, solde, utilisateurId from Compte where id=?";
        boolean bCreatedConnection = false;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (connexion == null) {
                LOG.trace("tentativ d'ouverture de connexion à partir du DAO");
                connexion = ConnectionUtil.getInstance().etablirConnexion();
                bCreatedConnection = true;
            }
            ps = connexion.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            compteEntity = fromResultSet(rs);
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (connexion != null && bCreatedConnection) {
                try {
                    LOG.trace("tentative de fermeture de connexion à partir du DAO");
                    ConnectionUtil.getInstance().fermerConnexion();
                } catch (SQLException e) {}
            }
        }
        return compteEntity;
    }

}
