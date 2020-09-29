package fr.exagone.jse.stream.api;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamObject {

	private static List<LigneTarifaire> lignesTarifaires = new ArrayList<LigneTarifaire>();
	
	private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private static Date dateDebut;
	private static Date dateFin;
	
	static {
		
		try {
			dateDebut = df.parse("15/01/2020");
			dateFin = df.parse("15/03/2020");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) throws ParseException {
		buildLignesTarifaires();
		// showContentList(lignesTarifaires.stream());
		filterList();
	}
	
	public static void filterList() {
		
		System.out.println("## FILTERING");
		Date dateDuJour = new Date();
		// Stream<LigneTarifaire> streamFilter = lignesTarifaires.stream().filter(element -> element.getDateDebut().before(dateDebut));
		
		
		List<LigneTarifaire> listeStandard = new ArrayList<>();
		// STREAM-FILTER
		Stream<LigneTarifaire> streamFilter = lignesTarifaires.stream().filter(e -> {
			
			Date dateDebut = e.getDateDebut();
		    long diffInMillies = Math.abs(dateDebut.getTime() - dateDuJour.getTime());
		    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			if (diff <= 3) {
				return true;
			} 
			listeStandard.add(e);
			
			return false;
			
		});
		List<LigneTarifaire> listeUrgent = streamFilter.collect(Collectors.toList());
		System.out.println("LISTE URGENTE");
		showContentList(listeUrgent);
		System.out.println("LISTE STANDARD");
		showContentList(listeStandard);		
		
	}

	public static void showContentList(List<LigneTarifaire> lignesTarifaires) {
		lignesTarifaires.stream().forEach(elt -> System.out.println(elt.getLibelle() + " - " + df.format(elt.getDateDebut()) ));
	}
	
	public static void buildLignesTarifaires() throws ParseException {
		// Saumon
		LigneTarifaire lt1 = new LigneTarifaire(1L, "Saumon", dateDebut,  dateFin, 5.0);
		// Pomme
		LigneTarifaire lt2 = new LigneTarifaire(1L, "Pomme", dateDebut,  dateFin, 1.0);
		// Poire
		LigneTarifaire lt3 = new LigneTarifaire(1L, "Poire", df.parse("08/01/2020"),  df.parse("15/03/2020"), 2.0);
		// Saumon
		LigneTarifaire lt4 = new LigneTarifaire(1L, "Avocat", df.parse("09/01/2020"),  df.parse("15/03/2020"), 3.0);
		// Galette
		LigneTarifaire lt5 = new LigneTarifaire(1L, "Galette", df.parse("09/01/2020"),  df.parse("15/03/2020"), 3.0);
		
		// ADD TO LIST
		lignesTarifaires.add(lt1);
		lignesTarifaires.add(lt2);
		lignesTarifaires.add(lt3);
		lignesTarifaires.add(lt4);
		lignesTarifaires.add(lt5);
		
	}
	
	
}
