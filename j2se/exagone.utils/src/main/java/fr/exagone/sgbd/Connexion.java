package fr.exagone.sgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

/**
 * Permet d'obtenir une connexion à la base.
 * @author grouault *
 */
public class Connexion {

	private Connection connexion = null;
	private Log logger = LogFactoryImpl.getLog(Connexion.class);
		
	public Connection getConnexion() throws SQLException {
		if (connexion == null) {
			//Etablisssement de la connexion.
			String urlConnection = "jdbc:oracle:thin:@192.168.0.1:1521:ORA11G";
			String user = "SURSAUD_DEV";
			String mdp = "SURSAUD_DEV";
			connexion = DriverManager.getConnection(urlConnection, user, mdp);
			logger.info("ouverture de la connexion");
		}
		return connexion;
	} 
	
	public void close() {
		try {
			if (connexion != null) {
				connexion.close();
				logger.info("fermeture de la connexion");
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}
	
	
}
