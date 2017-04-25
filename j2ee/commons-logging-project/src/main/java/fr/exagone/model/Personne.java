package fr.exagone.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Personne {

	private static Log logger = LogFactory.getLog(Personne.class);
	private String nom;
	private String prenom;
	
	public Personne(final String nom, final String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		if (logger.isTraceEnabled()) {
			logger.trace("Creation d'une nouvelle personne.");	
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

	public static void main(String[] args) {
		Personne p1 = new Personne("titi", "toto");
		BasicConfigurator.configure();
		Logger log = Logger.getLogger(Personne.class);
		log.setLevel(Level.TRACE);
		if (log.isTraceEnabled()) {
			log.trace("nom :" + p1.getNom());
		}
		
	}
	
}
