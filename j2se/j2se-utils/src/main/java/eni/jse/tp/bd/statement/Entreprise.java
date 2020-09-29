package eni.jse.tp.bd.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Entreprise {

	private String entreprise;
	private Connection connexion = null;
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Entreprise(String entreprise) {
		this.entreprise = entreprise;
	}

	public String getEntreprise() {
		return entreprise;
	}
	
	public void etablirConnexion(){
		try {
			this.connexion = DriverManager.getConnection("jdbc:mysql:///entreprise","root","41176");	
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	
	public void fermerConnexion(){
		try {
			this.connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void ajouterEmploye(Employe employe){
		this.etablirConnexion();
		try {
			//formatage de la date.
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String strDateNaiss = df.format(employe.getDatenaissance());
			Statement statement = this.connexion.createStatement();
			String strSql = "INSERT INTO EMPLOYE ";
					strSql += " VALUES('" + employe.getNom() + "',";
					strSql += "'" + employe.getPrenom() + "',";
					strSql += "'" +  strDateNaiss + "',";
					strSql += "'" + this.entreprise + "'";
					strSql += ")";
			statement.executeUpdate(strSql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.fermerConnexion();
		}
	}
	
	public int supprimerEmploye(Employe employe){
		int nbDelete = 0;
		this.etablirConnexion();
		try {
			String strSql = "DELETE FROM EMPLOYE WHERE";
					strSql += " nom = '" + employe.getNom() + "' ";
					strSql += " and prenom ='" + employe.getPrenom() + "';";
			Statement statement = this.connexion.createStatement();
			nbDelete = statement.executeUpdate(strSql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.fermerConnexion();
		}
		return nbDelete;
	}
	
	/**
	 * Renvoie la liste des employés de l'entreprise.
	 * @return
	 */
	public List<Employe> listeEmploye(){
		List<Employe> listEmploye = new ArrayList<Employe>();
		this.etablirConnexion();
		try {
			String strSql = "SELECT * FROM EMPLOYE WHERE";
					strSql += " entreprise = '" + this.entreprise + "';";	
			Statement statement = this.connexion.createStatement();
			ResultSet res = statement.executeQuery(strSql);
			while (res.next()) {
				Employe employe = new Employe(
						res.getString("nom"),
						res.getString("prenom"),
						res.getDate("datenaissance")
						);
				listEmploye.add(employe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.fermerConnexion();
		}
		return listEmploye;
	}
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
			Date datenaissance = sdf.parse("20/08/1986 13:00:00");
			Employe employe = new Employe("GUERIN","AURELIE", datenaissance);
			Entreprise entreprise = new Entreprise("Exagone");
			entreprise.ajouterEmploye(employe);
			System.out.println("Employé ajouté!");
			
//			int nbDelete = entreprise.supprimerEmploye(employe);
//			System.out.println("nombre d'Employé supprimé: " + nbDelete);
			
			List<Employe> list = entreprise.listeEmploye();
			System.out.println("Liste des employés de l'entreprise Exagone:");
			for (Iterator<Employe> iterator = list.iterator(); iterator.hasNext();) {
				Employe employeActif = (Employe) iterator.next();
				System.out.println(employeActif.toString());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
}
