package eni.jse.tp.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConnexionOracle {

	public static void main(String[] args) {
		
		Connection connexion = null;
		
		try {
			
			//chargement de la classe du driver par la JVM.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Etablisssement de la connexion.
			String urlConnection = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "factory";
			String mdp = "factory";
			connexion = DriverManager.getConnection(urlConnection, user, mdp);
			System.out.println("Connexion �tablie!");

			String strSql = "SELECT * FROM EMPLOYE WHERE DNO=?";
			PreparedStatement pstatement = connexion.prepareStatement(strSql);
			pstatement.setString(1, "1");
			ResultSet res = pstatement.executeQuery();
			
			while (res.next()) {
				String nom = res.getString("ENOM");
				String profession = res.getString("PROF");
				System.out.println("nom = " + nom + " - " + profession);
			}			
			
			
		} catch (Exception e) {
			
		} finally {
			if (connexion!=null) {
				try {
					connexion.close();
					System.out.println("connection ferm�e!");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		}
	
	}
	

	
}
