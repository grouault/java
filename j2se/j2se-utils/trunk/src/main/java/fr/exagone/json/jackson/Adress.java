package fr.exagone.json.jackson;

public class Adress {

	private String rue;
	private String ville;
	private Integer codePostal;
	
	public Adress(final String rue, final String ville, final Integer codePostal) {
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
	}
	
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}

	public Integer getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}
	
}
