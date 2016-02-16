package eni.jse.tp.bd.preparedStatement;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Employe {
	
	private String nom;
	private String prenom;
	private Date datenaissance;
	
	public Employe(String nom, String prenom, Date datenaissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.datenaissance = datenaissance;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public Date getDatenaissance() {
		return datenaissance;
	}
	
	public String toString(){
		DateFormat fd= new SimpleDateFormat("dd/MM/yyyy");
		return (this.getNom() + " " + this.getPrenom() + " n� le " + fd
				.format(this.getDatenaissance()));
//		return(this.getNom() + " " + this.getPrenom() + " n� le " +  this.getDatenaissance());
	}
}
