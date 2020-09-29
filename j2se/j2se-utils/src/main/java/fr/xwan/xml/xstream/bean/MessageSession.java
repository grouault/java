package fr.xwan.xml.xstream.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("MESSAGE")
public class MessageSession {

	@XStreamAlias("TEXTE")
   	@XStreamAsAttribute
	private String texte;
	
	@XStreamAlias("TEMPS")
   	@XStreamAsAttribute
	private String temps;

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public String getTemps() {
		return temps;
	}

	public void setTemps(String temps) {
		this.temps = temps;
	}
	
}
