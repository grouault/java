package fr.xwan.xml.xstream.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("message")
public class Message {

	@XStreamAlias("texte")
   	@XStreamAsAttribute
	private String texte;
	
	@XStreamAlias("temps")
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
