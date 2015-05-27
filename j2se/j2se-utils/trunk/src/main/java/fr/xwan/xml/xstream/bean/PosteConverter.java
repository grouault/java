package fr.xwan.xml.xstream.bean;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class PosteConverter implements Converter {

	private PosteSessionConverter posteSessionConverter;
	
	public PosteConverter() {
		posteSessionConverter = new PosteSessionConverter();
	}
	
	public boolean canConvert(Class type) {
		return type.equals(Poste.class);
	}

	public void marshal(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		// TODO Auto-generated method stub

	}

	/**
	 * xml --> Poste.
	 */
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {

		Poste poste = new Poste();
		List<PosteSession> sessions = new ArrayList<PosteSession>();
		
		// recuperation des attributs.
		String nomPoste =  reader.getAttribute("NOM");
		if (nomPoste != null && !"".equals(nomPoste)) {
			poste.setNom(nomPoste);
		}
		
		// recuperation de l etat du poste.
		String etatPoste = reader.getAttribute("ETAT_DEMARRAGE");
		if (etatPoste != null && !"".equals(etatPoste)) {
			poste.setEtat(etatPoste);
		}	
		
		// recuperation des sessions.
		 while ( reader.hasMoreChildren() ) {
			 reader.moveDown();	
			 if ("SESSION".equals(reader.getNodeName())) {
			 // on recupere les datas de la session courante.
				 PosteSession session = (PosteSession) posteSessionConverter.unmarshal(reader, context); 
				 sessions.add(session);
			 } 
			 reader.moveUp();
		 }
		 poste.setSessions(sessions);
		 
		 return poste;
		
	}

}
