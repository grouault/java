package eni.jse.tp.bd.JNDI;

import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import eni.jse.tp.bd.preparedStatement.Employe;



/**
 * @author Gildas
 * PreparedStatement et CallableStatement.
 */
public class Entreprise {

	private String nomEntreprise;
	private Connection connexion = null;

	public Entreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void etablirConnexion() {
		try {
			Properties env = System.getProperties();
			env.put("java.naming.factory.initial",
			        "com.sun.jndi.rmi.registry.RegistryContextFactory");
			env.put("java.naming.provider.url","rmi://localhost:1099");
			Context c = new InitialContext(env);	
			MysqlDataSource ds = (MysqlDataSource) c.lookup("jdbc/BD1"); 
			this.connexion = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void fermerConnexion() {
		try {
			this.connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ajouterEmploye(Employe employe) {
		this.etablirConnexion();
		try {
			// formatage de la date.
			// DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// String strDateNaiss = df.format(employe.getDatenaissance());
			String strSql = "INSERT INTO EMPLOYE VALUES(?,?,?,?)";
			PreparedStatement pstatement = this.connexion
					.prepareStatement(strSql);
			pstatement.setString(1, employe.getNom());
			pstatement.setString(2, employe.getPrenom());
			pstatement.setDate(3, employe.getDatenaissance());
			pstatement.setString(4, this.nomEntreprise);
			pstatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.fermerConnexion();
		}
	}

	public int supprimerEmploye(Employe employe) {
		int nbDelete = 0;
		this.etablirConnexion();
		try {
			String strSql = "DELETE FROM EMPLOYE WHERE nom=? and prenom=?";
			PreparedStatement pStatement = this.connexion
					.prepareStatement(strSql);
			pStatement.setString(1, employe.getNom());
			pStatement.setString(2, employe.getPrenom());
			nbDelete = pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.fermerConnexion();
		}
		return nbDelete;
	}

	public List<Employe> listeEmploye() {
		List<Employe> listEmploye = new ArrayList<Employe>();
		this.etablirConnexion();
		try {
			String strSql = "SELECT * FROM EMPLOYE WHERE entreprise=?";
			PreparedStatement pstatement = this.connexion
					.prepareStatement(strSql);
			pstatement.setString(1, this.nomEntreprise);
			ResultSet res = pstatement.executeQuery();
			while (res.next()) {
				Employe employe = new Employe(res.getString("nom"), res
						.getString("prenom"), res.getDate("datenaissance"));
				listEmploye.add(employe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.fermerConnexion();
		}
		return listEmploye;
	}

	public void ajouterEmployes(List<Employe> listEmployes) {
		this.etablirConnexion();
		try {
			if (!listEmployes.isEmpty()) {
				this.connexion.setAutoCommit(false);
				String strSql = "INSERT INTO EMPLOYE VALUES (?,?,?,?)";
				PreparedStatement pStatement = this.connexion
						.prepareStatement(strSql);
				// Parcours de la liste des employes
				for (Iterator<Employe> iterator = listEmployes.iterator(); iterator
						.hasNext();) {
					Employe employeCourant = (Employe) iterator.next();
					pStatement.clearParameters();
					pStatement.setString(1, employeCourant.getNom());
					pStatement.setString(2, employeCourant.getPrenom());
					pStatement.setDate(3, employeCourant.getDatenaissance());
					pStatement.setString(4, this.nomEntreprise);
					pStatement.addBatch();
				}
				// on peut récupérer le résultat dans un tableau d'entier.
				pStatement.executeBatch();
				this.connexion.commit();
				this.connexion.setAutoCommit(true);
			}
		} catch (BatchUpdateException e) {
			System.err.println("-----BatchUpdateException-----");
			System.err.println("SQLState:  " + e.getSQLState());
			System.err.println("Message:  " + e.getMessage());
			System.err.println("Vendor:  " + e.getErrorCode());
			System.err.println("Update counts:  ");
			int[] updateCounts = e.getUpdateCounts();
			for (int i = 0; i < updateCounts.length; i++) {
				System.err.println("tab[" + i + "]=" + updateCounts[i]);
			}
			System.err.println("");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.fermerConnexion();
		}
	}
	
	public List<Employe> listeEmployeFamille(String strNomFamille){
		List<Employe> listeEmploye = new ArrayList<Employe>();
		this.etablirConnexion();
		try{
			String strSql = "CALL employeFamille ('" + strNomFamille + "')";
			CallableStatement cStatement = this.connexion.prepareCall(strSql);
			ResultSet res = cStatement.executeQuery();
			while (res.next()){
				Employe employe = new Employe(
						res.getString("nom"),
						res.getString("prenom"),
						res.getDate("datenaissance")
					);
				listeEmploye.add(employe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			this.fermerConnexion();
		}
		
		return listeEmploye;
	}

	public static void main(String[] args) {
		try {
			// Création de l'entreprise
			Entreprise entrepriseP = new Entreprise("EXAGONE");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			// Création des employés.
			// employé1
			Date datenaissance = new Date(sdf.parse("20/8/1986").getTime());
			Employe employe = new Employe("GUERIN", "AURELIE", datenaissance);

			// employé2
			Date datenaissance1 = new Date(sdf.parse("20/05/1975").getTime());
			Employe employe1 = new Employe("GASTON", "HECTOR", datenaissance1);

//			employé3
			Employe employe3  = new Employe("ROUAULT","GILDAS", new Date(
					sdf.parse("04/11/1976").getTime()));
			
			// Ajouter un employé
			// entrepriseP.ajouterEmploye(employe);
			// System.out.println("Employé ajouté! : " + employe.toString());

			// Supprimer un employe
			// entrepriseP.supprimerEmploye(employe);
			// System.out.println("Suppression effectuée!");

			// Ajouter une liste d'employés
			List<Employe> listEmployes = new ArrayList<Employe>();
			listEmployes.add(employe);
			listEmployes.add(employe1);
			listEmployes.add(employe3);
			entrepriseP.ajouterEmployes(listEmployes);

			// lister les employés
			List<Employe> listEmploye = entrepriseP.listeEmploye();
			for (Iterator<Employe> iterator = listEmploye.iterator(); iterator
					.hasNext();) {
				System.out.println(((Employe) iterator.next()).toString());
			}
			
			// liste des employés dont le nom est rouault
			List<Employe> listEmployeRouault = entrepriseP.listeEmployeFamille("ROUAULT");
			System.out.println("Liste des employés dont le nom est 'ROUAULT':");
			if(!listEmployeRouault.isEmpty()){	
				for (Iterator<Employe> iterator = listEmployeRouault.iterator(); iterator
						.hasNext();) {
					System.out.println(((Employe) iterator.next()).toString());
				}
			}
			else{
				System.out.println("Aucun employé porte ce nom.");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
