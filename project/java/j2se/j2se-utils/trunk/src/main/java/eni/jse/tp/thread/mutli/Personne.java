package eni.jse.tp.thread.mutli;

public class Personne extends Thread{

	private String nom;
	private Calculateur calculateur;
	
	public Personne(String pNom, Calculateur pCalculateur) {
		super(pNom);
		this.nom = pNom;
		this.calculateur = pCalculateur;
	}
	
	@Override
	public void run() {
		//déclenchement de l'opération de calcul.
		calculateur.calcul();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
