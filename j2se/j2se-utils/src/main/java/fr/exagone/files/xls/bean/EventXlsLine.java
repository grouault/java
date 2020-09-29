package fr.exagone.files.xls.bean;

public class EventXlsLine implements XlsLine {

	private Integer lineNumber;
	
	private String titre;
	private String categorie;
	private String type;
	private String localisation;
	private String dateDebut;
	private String dateFin;
	private String thematique;
	private String publicCible;
	private String urlLien;
	private String description;
	private String evtNational;
	private String urlIllusatration;
	private String credit;
	private String tarifs;
	private String partenaires;
	private String contacts;
	private String datePublication;
	

	public String getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getDatePublication() {
		return datePublication;
	}
	public void setDatePublication(String datePublication) {
		this.datePublication = datePublication;
	}
	public Integer getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public String getDateFin() {
		return dateFin;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	public String getThematique() {
		return thematique;
	}
	public void setThematique(String thematique) {
		this.thematique = thematique;
	}
	public String getPublicCible() {
		return publicCible;
	}
	public void setPublicCible(String publicCible) {
		this.publicCible = publicCible;
	}
	public String getUrlLien() {
		return urlLien;
	}
	public void setUrlLien(String urlLien) {
		this.urlLien = urlLien;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getEvtNational() {
		return evtNational;
	}
	public void setEvtNational(String evtNational) {
		this.evtNational = evtNational;
	}
	public String getUrlIllusatration() {
		return urlIllusatration;
	}
	public void setUrlIllusatration(String urlIllusatration) {
		this.urlIllusatration = urlIllusatration;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getTarifs() {
		return tarifs;
	}
	public void setTarifs(String tarifs) {
		this.tarifs = tarifs;
	}
	public String getPartenaires() {
		return partenaires;
	}
	public void setPartenaires(String partenaires) {
		this.partenaires = partenaires;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	

	
}
