package fr.exagone.bd;

import fr.exagone.bd.dao.UtilisateurDAO;
import fr.exagone.bd.entity.UtilisateurEntity;
import fr.exagone.bd.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UtilisateurDAOTest {

    private static final Log LOG = LogFactoryImpl.getLog(UtilisateurDAOTest.class);

    private static Connection connexion;

    @BeforeClass
    public static void setUp() throws SQLException {
        connexion = ConnectionUtil.getInstance().etablirConnexion();
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        if (connexion != null) {
            ConnectionUtil.getInstance().fermerConnexion();
        }
    }

    @Test
    public void a1_select() {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        UtilisateurEntity utilisateur =  utilisateurDAO.findUtilisateur(1, connexion);
        LOG.trace("utilisateur " + utilisateur);
        Assert.assertNotNull(utilisateur);
    }

}
