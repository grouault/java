package eni.jse.tp.io.serialisation;

import java.io.Serializable;

public class Personne implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nom;
	private String prenom;
	private transient String initiaux;
	
	/**
	 * Constructeur
	 * @param nom
	 * @param prenom
	 */
	public Personne(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getInitiaux() {
		if(initiaux == null){
			if(nom!=null && prenom!=null){
				initiaux = Character.toUpperCase(nom.charAt(0)) + "." +  Character.toUpperCase(prenom.charAt(0));
			}
		}
		return initiaux;
	}
	
	public String toString(){
		return (this.getPrenom() + " " +this.getNom() + " - " + this.getInitiaux());
	}
	
	public static void main(String[] args) {
		Personne personne = new Personne("Rouault","Gildas");
		System.out.println("Info Personne: " + personne.toString());
	}
	
	
	
}
