package fr.exagone.jse.stream.api;

import java.util.Date;

public class LigneTarifaire {

	private Long id;
	
	private String libelle;
	
	private Date dateDebut;
	
	private Date dateFin;
	
	private Double prixRef;

	public LigneTarifaire(Long id, String libelle, Date dateDebut, Date dateFin, Double prixRef) {
		this.id = id;
		this.libelle = libelle;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.prixRef = prixRef;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Double getPrixRef() {
		return prixRef;
	}

	public void setPrixRef(Double prixRef) {
		this.prixRef = prixRef;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
