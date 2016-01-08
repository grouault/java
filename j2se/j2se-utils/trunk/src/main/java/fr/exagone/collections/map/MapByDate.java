package fr.exagone.collections.map;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import fr.exagone.beans.Occupation;
import fr.exagone.beans.Offre;
import fr.exagone.utils.CalendarUtil;

public class MapByDate {

	private List<Occupation> occupations = null;
	private List<Offre> offres = null;
	private Map<Date, List<Object>> produits = null;
	
	private static DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) {
		MapByDate mpByD = new MapByDate();
		mpByD.initProduits();
		if (mpByD.produits != null) {
			mpByD.showProduits();
		} else {
			System.out.println("Impossible de récupérer des produits");
		}
	}
	
	private void showProduits() {
		System.out.println("récupération des produits: \n");
		System.out.println("--------------------------------\n");
		for (Map.Entry<Date, List<Object>> entry : produits.entrySet()) {
			 Date mpKey = entry.getKey();
			 List<Object> mpValue = entry.getValue();
			 StringBuffer bffrOut = new StringBuffer();
			 bffrOut.append("date = " + formatDate.format(mpKey) + "\n");
			 for (Object obj : mpValue) {
				 if (obj instanceof Occupation) {
					 Occupation occ = (Occupation)obj;
					 bffrOut.append("occupation : " + occ.getTitre() + "\n");
				 }
				 if (obj instanceof Offre) {
					 Offre off = (Offre)obj;
					 bffrOut.append("offre : " + off.getTitre() + "\n");
				 }
			 }
			 bffrOut.append("--------------------------------\n");
			 System.out.println(bffrOut.toString());
		 }
	}
	
	private void initProduits() {

		// ajout d'occupations dans la liste.
		occupations = new ArrayList<Occupation>();
		Occupation occ1 = new Occupation();
		occ1.setId(1);
		occ1.setTitre("occupation 1");
		occ1.setDateDebut(CalendarUtil.getInstance().getDate("01/01/2015", "dd/MM/yyyy"));
		occupations.add(occ1);
		Occupation occ2 = new Occupation();
		occ2.setId(2);
		occ2.setTitre("occupation 2");
		occ2.setDateDebut(CalendarUtil.getInstance().getDate("05/01/2015", "dd/MM/yyyy"));	
		occupations.add(occ2);
		// ajout d'offres dans la liste.
		offres = new ArrayList<Offre>();
		Offre off1 = new Offre();
		off1.setTitre("offre 1");
		off1.setDateDebut(CalendarUtil.getInstance().getDate("01/01/2015", "dd/MM/yyyy"));
		offres.add(off1);
		Offre off2 = new Offre();
		off2.setId(2);
		off2.setTitre("offre 2");
		off2.setDateDebut(CalendarUtil.getInstance().getDate("07/01/2015", "dd/MM/yyyy"));		
		offres.add(off2);
		Offre off3 = new Offre();
		off3.setId(3);
		off3.setTitre("offre 3");
		off3.setDateDebut(CalendarUtil.getInstance().getDate("03/01/2015", "dd/MM/yyyy"));		
		offres.add(off3);
		// alimentation des produits.
		produits = new TreeMap<Date, List<Object>>();
		for(Occupation occ : occupations) {
			addValues(occ.getDateDebut(), occ);
		}
		for(Offre off : offres) {
			addValues(off.getDateDebut(), off);
		}		
	}
	
	public void addValues(Date date, Object object){
		List<Object> tmpList = null;
		if (produits.containsKey(date)) {
			tmpList = produits.get(date);
			tmpList.add(object);
		} else {
			tmpList = new ArrayList<Object>();
			tmpList.add(object);
		}
		produits.put(date, tmpList);
	}
	
	public List<Occupation> getOccupations() {
		return occupations;
	}

	public void setOccupations(List<Occupation> occupations) {
		this.occupations = occupations;
	}

	public List<Offre> getOffres() {
		return offres;
	}

	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}
	
	
	
	
}
