package fr.exagone.metier;

import java.io.Serializable;

public class Personne implements Serializable, Comparable<Personne> {

	private static final long serialVersionUID = -6339277323674832202L;
	
	private String nom;
	private String prenom;
	private Adresse adresse;
	private String ville;
	
	public Personne() {
	}
	
	public Personne(String pNom, String pPrenom) {
		this.nom = pNom;
		this.prenom=pPrenom;
	}
	
	public Personne(String prenom, String nom, String ville) {
		this.prenom = prenom;
		this.nom = nom;
		this.ville = ville;
	}
	
	public Personne(String prenom, String nom, Adresse adresse) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}
	
	/**
	 * tri dans l'ordre croissant.
	 */
	public int compareTo(Personne p) {
		if (this.nom.equals(p.nom)) {
			return this.prenom.compareToIgnoreCase(p.prenom);
		} else {
			return this.nom.compareToIgnoreCase(p.nom);
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
}
