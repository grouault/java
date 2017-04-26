package fr.exagone.main;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

import fr.exagone.sgbd.Connexion;

/**
 * @author grouault
 */
public class SgbdLauncher {

	private static Log logger = LogFactoryImpl.getLog(SgbdLauncher.class);
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			logger.error("impossible de charger le driver : " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		SgbdLauncher launcher = new SgbdLauncher();
		Boolean mode = true;
		logger.debug("args = " + args);
		if (args != null && args.length>0) {
			mode = Boolean.valueOf(args[0]);
		
		}
		launcher.launch(mode);
	}

	/**
	 * Lance la modification des mots de passe.
	 */
	public void launch(Boolean mode) {
		
		Connexion connexion = new Connexion();
		try {
			if (connexion.getConnexion() == null) {
				throw new SQLException("Impossible d'établir la connexion avec la base.");
			}
			
			logger.debug("TODO : appel du traitement");
			logger.debug("Mode =" + mode);
			
		} catch (SQLException e) {
			logger.error("Erreur grave : " + e.getMessage());
		} finally {
			connexion.close();
		}
		
	}
	
}
