package fr.xwan.xml.xstream.bean;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("SESSIONS")
public class Sessions {

	@XStreamAlias("MESSAGES_FIN_SESSION")
	List<MessageSession> messages;
	
	@XStreamAlias("POSTES")
	List<Poste> postes;

	public List<Poste> getPostes() {
		return postes;
	}

	public void setPostes(List<Poste> postes) {
		this.postes = postes;
	}
		
}
