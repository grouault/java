package eni.jse.tp.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Permet d'initialiser une connexion via jdbc et un connecteur
 * mis dans le classpath.
 * @author Gildas
 *
 */
public class TestConnexionMySQL {

	public static void main(String[] args) {
		
		Connection connexion = null;
		
		try {
			//chargement de la classe du driver par la JVM.
			Class.forName("com.mysql.jdbc.Driver");
			
			//Etablisssement de la connexion.
			connexion = DriverManager.getConnection("jdbc:mysql:///entreprise","root","41176");
			System.out.println("Connexion établie!");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(connexion!=null){
				try {
					connexion.close();
					System.out.println("Connection fermée!");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
