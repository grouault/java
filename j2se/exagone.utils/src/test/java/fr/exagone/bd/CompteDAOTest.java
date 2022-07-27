package fr.exagone.bd;

import fr.exagone.bd.dao.CompteDAO;
import fr.exagone.bd.entity.CompteEntity;
import fr.exagone.bd.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CompteDAOTest {

    private static final Log LOG = LogFactoryImpl.getLog(CompteDAOTest.class);

    private static Connection connexion;

    @BeforeClass
    public static void setUp() throws SQLException {
        connexion = ConnectionUtil.getInstance().etablirConnexion();
    }

    @AfterClass
    public static void tearDown() throws SQLException{
        if (connexion != null) {
            ConnectionUtil.getInstance().fermerConnexion();
        }
    }

    @Test
    public void a1_select() {
        CompteDAO compteDAO = new CompteDAO();
        CompteEntity compte = compteDAO.find(12, connexion);
        LOG.trace("Compte = " + compte.toString());
        Assert.assertNotNull(compte);
    }

}
