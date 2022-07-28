package fr.exagone.bd;

import fr.exagone.bd.dao.UtilisateurDAO;
import fr.exagone.bd.entity.ESex;
import fr.exagone.bd.entity.UtilisateurEntity;
import fr.exagone.bd.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UtilisateurDAOTest {

    private static final Log LOG = LogFactoryImpl.getLog(UtilisateurDAOTest.class);

    private static Connection connexion;
    private static UtilisateurDAO utilisateurDAO;

    private static UtilisateurEntity utilisateur_1;

    @BeforeClass
    public static void setUp() throws SQLException {
        connexion = ConnectionUtil.getInstance().etablirConnexion();
        utilisateurDAO = new UtilisateurDAO();
        utilisateur_1 = new UtilisateurEntity()
                .setNom("test").setPrenom("test").setLogin("test").setPwd("test")
                .setSex(ESex.HOMME).setDateDeNaissance(new Date(Calendar.getInstance().getTimeInMillis()) )
                .setAdresse("test").setCodePostal(00000).setTelephone("00");
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        if (connexion != null) {
            ConnectionUtil.getInstance().fermerConnexion();
        }
    }

    @Test
    public void a1_insert() {
        UtilisateurEntity createdUser = utilisateurDAO.insert(utilisateur_1, connexion);
        LOG.trace("created_user = " + createdUser);
        Assert.assertNotNull(createdUser);
    }

    @Test
    public void b1_select() {
        UtilisateurEntity utilisateur =  utilisateurDAO.find(1, connexion);
        LOG.trace("utilisateur " + utilisateur);
        Assert.assertNotNull(utilisateur);
    }

    @Test
    public void c1_udpate() {
        UtilisateurEntity utilisateur = utilisateurDAO.find(10, connexion);
        utilisateur.setAdresse("new adressse");
        utilisateur = utilisateurDAO.update(utilisateur, connexion);
        UtilisateurEntity updatedUser = utilisateurDAO.find(10, connexion);
        LOG.trace("utilisateur modifié = " + updatedUser);
        Assert.assertEquals(utilisateur, updatedUser);
    }

    @Test
    public void d1_delete(){
        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setId(7);
        Assert.assertTrue(utilisateurDAO.delete(utilisateurEntity, connexion));
    }

}
