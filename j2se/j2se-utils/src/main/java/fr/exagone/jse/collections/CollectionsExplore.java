package fr.exagone.jse.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import fr.exagone.metier.Personne;

/**
 * Remplacement de personne dans une liste.
 * @author Gildas
 *
 */
public class CollectionsExplore {
	
	public static void main(String[] args) {
		Personne p1 = new Personne("ROUAULT", "Gildas");
		Personne p2 = new Personne("ROUAULT", "Renée");
		Personne p3 = new Personne("GUERIN", "Aurélie");

		List<Personne> lstPersonne = new ArrayList<Personne>();
		lstPersonne.add(p1);
		lstPersonne.add(p3);	
		
		Collections.replaceAll(lstPersonne, p1, p2);
		for (Iterator<Personne> iterator = lstPersonne.iterator(); iterator.hasNext();) {
			Personne currentPersonne = (Personne) iterator.next();
			System.out.println(currentPersonne.getPrenom());
		}
		
		
	}
	
}
