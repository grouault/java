package fr.xwan.xml.xstream.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class PosteSessionConverter implements Converter {

	private DateFormat formatDate;
	private DateFormat formatHour;
	
	public PosteSessionConverter() {
		this.formatDate = new SimpleDateFormat("yyyyMMdd");
		this.formatHour = new SimpleDateFormat("yyyyMMdd hh:mm");
	}
	
	public boolean canConvert(Class type) {
		return type.equals(PosteSession.class);
	}

	public void marshal(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		System.out.println("in");

	}

	/**
	 * xml to PosteSession
	 */
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
	
		PosteSession posteSession = new PosteSession();		
		try {
			
			// recuperation de l'heure de debut.
			String strDateDebut = reader.getAttribute("date_debut"); 
			if (strDateDebut != null && !"".equals(strDateDebut)) {
				Calendar calDateDeb = Calendar.getInstance();
				calDateDeb.setTime(formatDate.parse(strDateDebut));
				posteSession.setDateDebut(calDateDeb);
			} 
			
			// recuperation de l'heure de fin.
			String strDateFin = reader.getAttribute("date_fin"); 
			if (strDateFin != null && !"".equals(strDateFin)) {
				Calendar calDateFin = Calendar.getInstance();
				calDateFin.setTime(formatDate.parse(strDateFin));
				posteSession.setDateFin(calDateFin);
			} 
			
			// traitement de l'heure de début.
			String strHeureDebut = reader.getAttribute("heure_debut");
			Calendar calHeureDebut = Calendar.getInstance();
			if (strHeureDebut != null && !"".equals(strHeureDebut)) {
				int year = calHeureDebut.get(Calendar.YEAR);
				String day = strDateDebut != null && !"".equals(strDateDebut) ? strDateDebut : String.valueOf(year).concat("01").concat("01") ;
				calHeureDebut.setTime(formatHour.parse(day.concat(" ").concat(strHeureDebut)));
				posteSession.setHeureDebut(calHeureDebut);
			}
			
			// traitement de l'heur de fin.
			String strHeureFin = reader.getAttribute("heure_fin");
			Calendar calHeureFin = Calendar.getInstance();
			if (strHeureFin != null && !"".equals(strHeureFin)) {
				int year = calHeureFin.get(Calendar.YEAR);
				String day = strDateFin != null && !"".equals(strDateFin) ? strDateFin : String.valueOf(year).concat("01").concat("01") ;
				calHeureFin.setTime(formatHour.parse(day.concat(" ").concat(strHeureFin)));
				posteSession.setHeureFin(calHeureFin);
			}
			
			// recuperation du timeout.
			String timeOut = reader.getAttribute("time_out");
			if (timeOut != null && !"".equals(timeOut)) {
				posteSession.setTimeOut(Integer.parseInt(timeOut));
			}
			
			// traitement de la liste des jours.
			String jours = reader.getAttribute("jour");
			if (jours != null && !"".equals(jours)) {
				String[] tabJours = jours.split(",");
				posteSession.setJours(Arrays.asList(tabJours));
				
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return posteSession;
	}

}
